import { Column, Entity, PrimaryGeneratedColumn } from "typeorm"

@Entity()
export class Workout {
    @PrimaryGeneratedColumn()
    id: number

    @Column()
    name: string

    @Column()
    description: string

    @Column()
    calories: number
}