import { DataSource } from "typeorm";
import * as dotenv from "dotenv";
import { User } from "./src/entity/user.entity";
import { Token } from "./src/entity/token.entity";
import { Food } from "./src/entity/food.entity";
import { Consumption } from "./src/entity/consumption.entity";
import { Diet } from "./src/entity/diet.entity";
import { Workout } from "./src/entity/workout.entity";
// import { Food } from "./src/entity/food.entity"
// import { Consumption } from "./src/entity/consumption.entity"
dotenv.config();

export const myDataSource = new DataSource({
  type: "mysql",
  host: process.env.db_host,
  port: 3306,
  username: process.env.db_user,
  password: process.env.db_password,
  database: process.env.db_name,
  entities: [User, Token, Food, Consumption, Diet, Workout],
  logging: true,
  synchronize: false,
});
