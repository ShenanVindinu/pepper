package lk.ijse.dto;

public class RecipeDto {


    private String recipe_id;
    private String recipe_name;
    private String ingredient_name;

    public RecipeDto() {}

    public RecipeDto(String recipe_id, String recipe_name, String ingredient_name) {

        this.ingredient_name = ingredient_name;
        this.recipe_name = recipe_name;
        this.recipe_id = recipe_id;

    }

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name = ingredient_name;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "recipe_id='" + recipe_id + '\'' +
                ", Recipe_name='" + recipe_name + '\'' +
                ", ingredient_name='" + ingredient_name + '\'' +
                '}';
    }

}
