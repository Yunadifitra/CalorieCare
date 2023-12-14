import { DataSource } from "typeorm";
import { User } from "./src/entity/user.entity";
import { Token } from "./src/entity/token.entity";
import { Food } from "./src/entity/food.entity";
import { Consumption } from "./src/entity/consumption.entity";
import { Diet } from "./src/entity/diet.entity";
import { Workout } from "./src/entity/workout.entity";
// import { Food } from "./src/entity/food.entity"
// import { Consumption } from "./src/entity/consumption.entity"

export const myDataSource = new DataSource({
  type: "mysql",
  host: "localhost",
  port: 3306,
  username: "root",
  password: "",
  database: "capstone",
  entities: [User, Token, Food, Consumption, Diet, Workout],
  logging: true,
  synchronize: false,
});
