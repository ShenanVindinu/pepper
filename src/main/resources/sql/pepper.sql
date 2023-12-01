create database pepper;
use pepper;

create table allergy
(
    ingredient varchar(50) null
);

create index ingredient_id
    on allergy (ingredient);

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

create table recipe
(
    recipe_id       varchar(100)  not null
        primary key,
    recipe_name     varchar(200)  null,
    ingredient_name varchar(1000) null
);

create table category
(
    category_name varchar(35)  null,
    recipe_id     varchar(100) null,
    constraint category_ibfk_1
        foreign key (recipe_id) references recipe (recipe_id)
            on update cascade on delete cascade
);

create index recipe_id
    on category (recipe_id);

create table user
(
    user_id            varchar(15) not null
        primary key,
    user_name          varchar(15) null,
    sha1_hash          varchar(40) null,
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
    recipe_id varchar(100) null,
    constraint fk_recipe_id
        foreign key (recipe_id) references recipe (recipe_id)
);



INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('0', 'Miso-Butter Roast Chicken With Acorn Squash Panzanella', 'Chicken, Acorn, Squash, Butter');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('1', 'Crispy Salt and Pepper Potatoes', 'Salt, Pepper, Potatoes');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('10', 'Trinidad Curry Powder', 'Salt, Pepper Powder, Pepper, Cinnamon Powder');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('11', 'Best Deviled Eggs', 'Eggs, Pepper, Carrot, Onion');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('12', 'Pumpkin Dutch Baby With Pumpkin Butter', 'Pumpkin, Butter');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('13', 'Enfrijoladas', 'Corn, Vegetable Oil, Vegetables');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('14', 'Caramelized Plantain Parfait', 'Caramel, Eggs, Coconut oil, Coconut');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('15', 'Chicken and Potato Gratin With Brown Butter Cream', 'Potato, Chicken, Gratin, Brown Butter');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('16', 'Roasted Beets With Crispy Sunchokes and Pickled Orange-Ginger Purée', 'Beets, Ginger, Orange, Sunchokes');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('2', 'Thanksgiving Mac and Cheese', 'Cheese, Bread');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('4', 'Newton\'s Law', 'Lemon, Tea Bags, Mint');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('6', 'Spiced Lentil and Caramelized Onion Baked Eggs', 'Spices, Onion, Eggs');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('8', 'Spicy Coconut Pumpkin Soup', 'Pepper, Chillies, Pumpkin, Coconut');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('9', 'Gorditas con Camarones', 'Flour, Water, Vegetables');


INSERT INTO pepper.category (category_name, recipe_id) VALUES ('American', '0');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Thai', '1');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('American', '2');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('American', '4');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Thai', '6');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Indian', '8');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Indian', '10');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Sri Lankan', '11');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('American', '12');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('American', '9');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('American', '13');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('American', '14');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Sri Lankan', '15');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Indian', '16');


-- Inserting new recipes into the recipe table
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('17', 'Spanish Omelette', 'Eggs, Potatoes, Onions');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('18', 'Japanese Ramen', 'Ramen Noodles, Pork, Egg, Green Onion');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('19', 'Italian Margherita Pizza', 'Pizza Dough, Tomatoes, Fresh Mozzarella, Basil');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('20', 'Mexican Guacamole', 'Avocado, Tomato, Onion, Lime, Cilantro');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('21', 'French Croissant', 'Flour, Butter, Yeast, Milk');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('22', 'Chinese Kung Pao Chicken', 'Chicken, Peanuts, Bell Peppers, Chili Peppers');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('23', 'Greek Salad', 'Cucumbers, Tomatoes, Feta Cheese, Olives');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('24', 'Indian Butter Chicken', 'Chicken, Tomatoes, Cream, Spices');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('25', 'Thai Green Curry', 'Chicken, Coconut Milk, Green Curry Paste, Vegetables');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('26', 'Japanese Sushi Rolls', 'Sushi Rice, Nori, Fish, Vegetables');

-- Inserting corresponding categories for the new recipes in the category table
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Spanish', '17');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Japanese', '18');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Italian', '19');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Mexican', '20');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('French', '21');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Chinese', '22');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Greek', '23');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Indian', '24');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Thai', '25');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Japanese', '26');



-- Inserting more recipes into the recipe table
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('27', 'Mexican Tacos', 'Tortillas, Beef, Lettuce, Cheese, Salsa');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('28', 'Indian Samosa', 'Potatoes, Peas, Spices, Pastry Dough');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('29', 'Italian Tiramisu', 'Ladyfingers, Coffee, Mascarpone Cheese, Cocoa Powder');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('30', 'Chinese Fried Rice', 'Rice, Eggs, Vegetables, Soy Sauce');


-- Inserting corresponding categories for the new recipes in the category table
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Mexican', '27');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Indian', '28');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Italian', '29');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Chinese', '30');


-- Inserting more recipes into the recipe table
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('31', 'Japanese Ramen', 'Noodles, Broth, Pork, Egg, Seaweed');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('32', 'French Onion Soup', 'Onions, Beef Broth, Bread, Cheese');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('33', 'Greek Moussaka', 'Eggplant, Potatoes, Ground Meat, Béchamel Sauce');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('34', 'Spanish Paella', 'Rice, Saffron, Chicken, Seafood, Bell Peppers');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('35', 'Thai Green Curry', 'Coconut Milk, Green Chili, Chicken, Vegetables');
-- Continue adding recipes...

-- Inserting corresponding categories for the new recipes in the category table
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Japanese', '31');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('French', '32');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Greek', '33');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Spanish', '34');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Thai', '35');


-- Inserting more recipes into the recipe table
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('36', 'Vietnamese Pho', 'Rice Noodles, Beef Broth, Beef Slices, Herbs');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('37', 'Italian Lasagna', 'Lasagna Sheets, Tomato Sauce, Ground Beef, Cheese');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('38', 'Mexican Tacos', 'Tortillas, Beef, Lettuce, Cheese, Salsa');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('39', 'Indian Butter Chicken', 'Chicken, Butter, Cream, Spices');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('40', 'Middle Eastern Hummus', 'Chickpeas, Tahini, Olive Oil, Garlic');
-- Add more recipes...

-- Inserting corresponding categories for the new recipes in the category table
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Vietnamese', '36');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Italian', '37');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Mexican', '38');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Indian', '39');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Middle Eastern', '40');

-- Inserting more recipes into the recipe table
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('41', 'Japanese Sushi Rolls', 'Rice, Nori, Fish, Vegetables');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('42', 'Spanish Paella', 'Rice, Seafood, Chicken, Saffron');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('43', 'Thai Green Curry', 'Green Curry Paste, Coconut Milk, Chicken, Vegetables');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('44', 'French Onion Soup', 'Onions, Beef Broth, Bread, Cheese');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('45', 'Greek Salad', 'Tomatoes, Cucumbers, Feta Cheese, Olives');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('46', 'Brazilian Feijoada', 'Black Beans, Pork, Sausage, Rice');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('47', 'Korean Kimchi Fried Rice', 'Kimchi, Rice, Vegetables, Eggs');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('48', 'Moroccan Tagine', 'Meat, Vegetables, Dried Fruits, Spices');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('49', 'Peruvian Ceviche', 'Fish, Lime Juice, Onions, Chili Peppers');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('50', 'Russian Borscht', 'Beets, Cabbage, Potatoes, Beef');
-- Add more recipes...

-- Inserting corresponding categories for the new recipes in the category table
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Japanese', '41');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Spanish', '42');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Thai', '43');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('French', '44');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Greek', '45');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Brazilian', '46');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Korean', '47');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Moroccan', '48');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Peruvian', '49');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Russian', '50');

-- Inserting new recipes into the recipe table
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('51', 'Mushroom Risotto', 'Arborio Rice, Mushroom, White Wine, Parmesan Cheese');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('52', 'Caprese Salad', 'Tomatoes, Mozzarella Cheese, Basil, Balsamic Vinegar');
INSERT INTO pepper.recipe (recipe_id, recipe_name, ingredient_name) VALUES ('53', 'Grilled Salmon with Lemon-Herb Butter', 'Salmon, Lemon, Butter, Dill, Garlic');






-- Assigning categories to Recipe 51, 52, and 53
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Italian', '51');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Italian', '52');
INSERT INTO pepper.category (category_name, recipe_id) VALUES ('Seafood', '53');


    --use shrimp or honey to test transaction model. they are unique recipes.






