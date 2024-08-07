-- Создание таблиц

create table if not exists task(
    id serial primary key,
    name text not null
);

create table if not exists dataset(
    id serial primary key,
    name text not null,
    description text,
    task_id bigint,
    foreign key (task_id) references task(id)
);

create table if not exists file(
    id serial primary key,
    dataset_id bigint,
    key text,
    foreign key (dataset_id) references dataset(id) on delete set null
);

-- Наполнение данных

INSERT INTO task (name) VALUES ('CLASSIFICATION');
INSERT INTO task (name) VALUES ('OBJECT_DETECTION');
INSERT INTO task (name) VALUES ('SEGMENTATION');