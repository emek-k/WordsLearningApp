package edu.pb.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import edu.pb.model.words.Word;

import java.lang.reflect.Type;

public class WordAdapter implements JsonSerializer<Word> {
    @Override
    public JsonElement serialize(Word word, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", word.getName());
        jsonObject.addProperty("definition", word.getDefinition());
        jsonObject.addProperty("translations", word.getTranslation());
        // Dodaj inne właściwości, które chcesz uwzględnić w JSON
        return jsonObject;
    }
}
