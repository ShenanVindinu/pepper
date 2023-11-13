create table ingredient
(
    ingredient_id   varchar(50) not null
        primary key,
    ingredient_name varchar(50) null
);

create table nutrition
(
    nutrition_id     varchar(20) not null
        primary key,
    nutrition_name   varchar(35) null,
    nutrition_amount int         null,
    ingredient_id    varchar(50) null,
    constraint nutrition_ibfk_1
        foreign key (ingredient_id) references ingredient (ingredient_id)
);

create index ingredient_id
    on nutrition (ingredient_id);

create table nutrition_detail
(
    nutrition_id  varchar(20) null,
    ingredient_id varchar(50) null,
    constraint nutrition_detail_ibfk_1
        foreign key (nutrition_id) references nutrition (nutrition_id),
    constraint nutrition_detail_ibfk_2
        foreign key (ingredient_id) references ingredient (ingredient_id)
);

create index ingredient_id
    on nutrition_detail (ingredient_id);

create index nutrition_id
    on nutrition_detail (nutrition_id);

create table recipe
(
    recipe_id       varchar(100) not null
        primary key,
    recipe_name     varchar(50)  null,
    ingredient_name varchar(30)  null
);

create table category
(
    category_name varchar(35)  null,
    recipe_id     varchar(100) null,
    constraint category_ibfk_1
        foreign key (recipe_id) references recipe (recipe_id)
);

create index recipe_id
    on category (recipe_id);

create table recipe_ingredient_detail
(
    recipe_id     varchar(100) null,
    ingredient_id varchar(50)  null,
    constraint recipe_ingredient_detail_ibfk_1
        foreign key (recipe_id) references recipe (recipe_id),
    constraint recipe_ingredient_detail_ibfk_2
        foreign key (ingredient_id) references ingredient (ingredient_id)
);

create index ingredient_id
    on recipe_ingredient_detail (ingredient_id);

create index recipe_id
    on recipe_ingredient_detail (recipe_id);

create table user
(
    user_id            varchar(15) not null
        primary key,
    user_name          varchar(15) null,
    password           varchar(15) null,
    profile_picture_id varchar(10) null
);

create table achivement
(
    achivement_id      varchar(20) not null
        primary key,
    achivement_name    varchar(30) null,
    achivement_granted varchar(10) null,
    user_id            varchar(15) null,
    constraint achivement_ibfk_1
        foreign key (user_id) references user (user_id)
);

create index user_id
    on achivement (user_id);

create table allergy
(
    ingredient_id varchar(50) null,
    user_id       varchar(15) null,
    constraint allergy_ibfk_1
        foreign key (ingredient_id) references ingredient (ingredient_id),
    constraint allergy_ibfk_2
        foreign key (user_id) references user (user_id)
);

create index ingredient_id
    on allergy (ingredient_id);

create index user_id
    on allergy (user_id);

create table collection
(
    collection_id   varchar(50) not null
        primary key,
    collection_name varchar(50) null,
    user_id         varchar(15) null,
    constraint collection_ibfk_1
        foreign key (user_id) references user (user_id)
);

create index user_id
    on collection (user_id);

create table recipe_collection_detail
(
    collection_ids varchar(50)  null,
    recipe_ids     varchar(100) null,
    constraint recipe_collection_detail_ibfk_1
        foreign key (collection_ids) references collection (collection_id),
    constraint recipe_collection_detail_ibfk_2
        foreign key (recipe_ids) references recipe (recipe_id)
);

create index collection_ids
    on recipe_collection_detail (collection_ids);

create index recipe_ids
    on recipe_collection_detail (recipe_ids);

create table reward
(
    reward_id     varchar(30) not null
        primary key,
    reward_name   varchar(30) null,
    achivement_id varchar(20) null,
    constraint reward_ibfk_1
        foreign key (achivement_id) references achivement (achivement_id)
);

create index achivement_id
    on reward (achivement_id);

create table user_recipe_detail
(
    recipe_id varchar(100) null,
    user_id   varchar(15)  null,
    constraint user_recipe_detail_ibfk_1
        foreign key (recipe_id) references recipe (recipe_id)
            on update cascade on delete cascade,
    constraint user_recipe_detail_ibfk_2
        foreign key (user_id) references user (user_id)
);

create index recipe_id
    on user_recipe_detail (recipe_id);

create index user_id
    on user_recipe_detail (user_id);

create table user_wish_list_details
(
    user_id   varchar(15)  null,
    recipe_id varchar(100) null,
    constraint user_wish_list_details_ibfk_1
        foreign key (user_id) references user (user_id),
    constraint user_wish_list_details_ibfk_2
        foreign key (recipe_id) references recipe (recipe_id)
);

create index recipe_id
    on user_wish_list_details (recipe_id);

create index user_id
    on user_wish_list_details (user_id);

create table wish_list
(
    wish_list_id varchar(30) not null
        primary key
);

