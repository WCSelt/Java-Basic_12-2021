package com.otus.homeworks.homework21;

public class HomeWork {
    public static void main(String[] args) {
        //docker commands:
        //запуск docker hello-world
        System.out.println("docker run hello-world");
        //размер образа hello-world
        System.out.println("docker image ls");
        //просмотр запущенных контейнеров
        System.out.println("docker ps");
        //запуск контейнера с образом убунту
        System.out.println("docker run -it ubuntu bash");
        //создаём каталог test в home
        System.out.println("mkdir /home/test");
        //запуск postgresql в контейнере
        System.out.println("docker run \\ " +
                "-p 5432:5432 \\ " +
                "-e POSTGRES_PASSWORD=postgres \\ " +
                "-e POSTGRES_DB=mydb \\ " +
                "-v \"$(pwd)/init_db\":/docker-entrypoint-initdb.d \\ " +
                "-d \\ " +
                "--name postgres \\ " +
                "postgres:14");
        //удаляем контейнер
        System.out.println("docker rm 348139dc38f7");
        //останавливаем контейнер
        System.out.println("docker stop 3f52981a3abb");
    }
}
