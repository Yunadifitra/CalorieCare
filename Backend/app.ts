import * as express from "express";
import * as dotenv from "dotenv";
import * as jwt from "jsonwebtoken";
import { Request, Response } from "express";
import { myDataSource } from "./app-data-source";
import { User } from "./src/entity/user.entity";
import { Token } from "./src/entity/token.entity";
import { authenticateJWT } from "./authMiddleware";
import { Food } from "./src/entity/food.entity";
import { Consumption } from "./src/entity/consumption.entity";
import { Workout } from "./src/entity/workout.entity";
import { LessThan, LessThanOrEqual, MoreThan, MoreThanOrEqual } from "typeorm";
import { Diet } from "./src/entity/diet.entity";
import { getWeek, max } from "date-fns";

dotenv.config();

myDataSource
  .initialize()
  .then(() => {
    console.log("Data Source has been initialized!");
  })
  .catch((err) => {
    console.error("Error during Data Source initialization:", err);
  });

const app = express();
app.use(express.json());

app.post("/register", async function (req: Request, res: Response) {
  const { name, email, password } = req.body;
  const user = new User();
  user.name = name;
  user.email = email;
  user.password = password;
  await myDataSource.getRepository(User).save(user);
  const token = jwt.sign({ userId: user.id, name: user.name }, process.env.SECRET_KEY, { expiresIn: "1h" });
  const tokenSave = new Token();
  tokenSave.token = token;
  await myDataSource.getRepository(Token).save(tokenSave);
  res.send({ message: "User registered successfully.", token });
});

app.post("/login", async function (req: Request, res: Response) {
  const { email, password } = req.body;
  const user = await myDataSource.getRepository(User).findOne({ where: { email } });
  if (!user || user.password !== password) {
    res.status(401).send({ message: "Invalid email or password." });
    return;
  }

  const token = jwt.sign({ userId: user.id, name: user.name }, process.env.SECRET_KEY, { expiresIn: "1h" });
  const tokenSave = new Token();
  tokenSave.token = token;
  await myDataSource.getRepository(Token).save(tokenSave);
  res.send({ message: "User login successfully", token });
});

app.post("/logout", authenticateJWT, async function (req: Request, res: Response) {
  const token = req.header("Authorization")?.split(" ")[1];
  const logoutStatus = await myDataSource.getRepository(Token).findOne({ where: { token } });
  await myDataSource.getRepository(Token).update(logoutStatus.id, { is_logout: true });
  res.send({ message: "Logout successfully." });
});

app.get("/foods", authenticateJWT, async function (req: Request, res: Response) {
  const search = req.query.search;
  let query = myDataSource.getRepository(Food).createQueryBuilder("food");
  if (search) {
    query.where("food.name LIKE :name", { name: `%${search}%` });
  }
  const data = await query.getMany();
  res.send(data);
});

app.post("/consumptions", authenticateJWT, async function (req: Request, res: Response) {
  const { food_id } = req.body;
  const user_id = req["user"].userId;
  const data = new Consumption();
  data.food = food_id;
  data.user = user_id;
  await myDataSource.getRepository(Consumption).save(data);
  res.send({ message: "Data saved successfully" });
});

app.get("/consumptions", authenticateJWT, async function (req: Request, res: Response) {
  const user_id = req["user"].userId;
  const currentDate = new Date();
  const year = currentDate.getFullYear();
  const month = (currentDate.getMonth() + 1).toString().padStart(2, "0");
  const day = currentDate.getDate().toString().padStart(2, "0");

  const data = await myDataSource
    .getRepository(Consumption)
    .createQueryBuilder("consumptions")
    .leftJoinAndSelect("consumptions.food", "food")
    .where("consumptions.user_id = :user_id", { user_id })
    .andWhere("YEAR(consumptions.created_at) = :year", { year })
    .andWhere("MONTH(consumptions.created_at) = :month", { month })
    .andWhere("DAY(consumptions.created_at) = :day", { day })
    .getMany();
  res.send({ data });
});

app.get("/summary", authenticateJWT, async function (req: Request, res: Response) {
  const user_id = req["user"].userId;
  const periode = req.query.periode;

  const currentDate = new Date();
  const weekNumber = getWeek(currentDate);
  const yesterday = new Date(new Date().setDate(new Date().getDate() - 1));
  const year = [currentDate.getFullYear(), yesterday.getFullYear()];
  const month = [(currentDate.getMonth() + 1).toString().padStart(2, "0"), (yesterday.getMonth() + 1).toString().padStart(2, "0")];
  const day = [currentDate.getDate().toString().padStart(2, "0"), yesterday.getDate().toString().padStart(2, "0")];

  const countThisMonth = await myDataSource.getRepository(Consumption).createQueryBuilder("consumptions").where("consumptions.user_id = :user_id", { user_id }).groupBy("SUBSTRING(consumptions.created_at,1,10)").getMany();

  const countThisWeek = await myDataSource
    .getRepository(Consumption)
    .createQueryBuilder("consumptions")
    .where("consumptions.user_id = :user_id", { user_id })
    .andWhere("WEEK(consumptions.created_at) = :weekNumber", { weekNumber })
    .groupBy("SUBSTRING(consumptions.created_at,1,10)")
    .getMany();

  let query = myDataSource.getRepository(Consumption).createQueryBuilder("consumptions");

  if (periode === "monthly") {
    query
      .select("SUBSTRING(consumptions.created_at,1,7) as date")
      .addSelect("SUM(food.calories)", "sum")
      .leftJoin("consumptions.food", "food")
      .where("consumptions.user_id = :user_id", { user_id })
      .andWhere("YEAR(consumptions.created_at) = :year", { year: currentDate.getFullYear() })
      .andWhere("MONTH(consumptions.created_at) = :month", { month: (currentDate.getMonth() + 1).toString().padStart(2, "0") })
      .groupBy("SUBSTRING(consumptions.created_at,1,7)");
  } else if (periode === "weekly") {
    query
      .select("WEEK(consumptions.created_at) as week")
      .addSelect("SUM(food.calories)", "sum")
      .leftJoin("consumptions.food", "food")
      .where("consumptions.user_id = :user_id", { user_id })
      .andWhere("WEEK(consumptions.created_at) = :weekNumber", { weekNumber })
      .groupBy("WEEK(consumptions.created_at)");
  } else {
    query
      .select("SUBSTRING(consumptions.created_at,1,10) as date")
      .addSelect("SUM(food.calories)", "sum")
      .leftJoin("consumptions.food", "food")
      .where("consumptions.user_id = :user_id", { user_id })
      .andWhere("YEAR(consumptions.created_at) IN (:...year)", { year })
      .andWhere("MONTH(consumptions.created_at) IN (:...month)", { month })
      .andWhere("DAY(consumptions.created_at) IN (:...day)", { day })
      .groupBy("SUBSTRING(consumptions.created_at,1,10)")
      .orderBy("consumptions.created_at", "ASC");
  }
  const data = await query.getRawMany();
  const maxCalories = 2200;

  let workout = [];
  let diet = [];
  let totalCalories;

  if (periode === "monthly") {
    totalCalories = data[0]["sum"] / countThisMonth.length;
    data[0]["sum"] = totalCalories;
  } else if (periode === "weekly") {
    totalCalories = data[0]["sum"] / countThisWeek.length;
    data[0]["sum"] = totalCalories;
  } else {
    if (data.length > 1) {
      totalCalories = data[0].sum;
    } else {
      totalCalories = 0;
    }
  }

  if (totalCalories > maxCalories) {
    const gapCalories = totalCalories - maxCalories;
    //penentuan rekomendasi workout
    workout = await myDataSource.getRepository(Workout).find({
      where: {
        calories: MoreThanOrEqual(gapCalories),
      },
      take: 3,
      order: {
        calories: "ASC",
      },
    });
    //penentuan rekomendasi diet
    diet = await myDataSource.getRepository(Diet).find({
      where: {
        calories: LessThanOrEqual(maxCalories - gapCalories),
      },
      take: 3,
      order: {
        calories: "DESC",
      },
    });
  }

  res.send({ summary: data, workout, diet });
});

app.delete("/consumptions", authenticateJWT, async function (req: Request, res: Response) {
  const { id } = req.body;
  const user_id = req["user"].userId;
  const data = await myDataSource.getRepository(Consumption).findOne({
    where: {
      id,
      user: { id: user_id },
    },
  });
  if (!data) {
    res.status(404).send({ message: "Data Not Found" });
    return;
  }
  await myDataSource.getRepository(Consumption).remove(data);
  res.send({ message: "Data deleted successfully" });
});

app.listen(process.env.PORT, (): void => {
  console.log(`server udah jalan ${process.env.PORT}`);
});
