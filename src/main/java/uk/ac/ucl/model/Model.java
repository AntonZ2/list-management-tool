package uk.ac.ucl.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.ucl.lists.Item;
import uk.ac.ucl.lists.ListData;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.nio.file.Files;

public class Model {
  // The example code in this class should be replaced by your Model class code.
  // The data should be stored in a suitable data structure.
  private LinkedHashMap<String, ListData> listsMap;

  public Model() {
    listsMap = new LinkedHashMap<>();
  }

  public List<String> getListNames() {
    return new ArrayList<>(listsMap.keySet());
  }

  public ListData getList(String listName) {
    return listsMap.get(listName);
  }


  public void addList(String listName, ListData list) {
    listsMap.put(listName, list);
    updateFile();
  }

  public void removeList(String listName) {
    listsMap.remove(listName);
    updateFile();
  }

  public void renameList(String oldName, String newName) {
    ListData list = getList(oldName);
    if (list != null) {
      removeList(oldName);
      addList(newName, list);
      updateFile();
    }
  }

  public void addItem(String listName, String itemType, String itemKey, String itemData) {
    Item newItem = Item.createItem(itemType, itemKey, itemData);
    ListData list = getList(listName);
    if (list != null) {
      list.addItem(newItem);
    }
  }

  public void deleteItem(String listName, int itemInd) {
    ListData list = getList(listName);
    if (list != null) {
      list.removeItem(itemInd);
    }
  }

  public void updateItem(String listName, int itemInd, String newData) {
    ListData list = getList(listName);
    if (list != null) {
      Item item = list.getItem(itemInd);
      if (item != null) {
        item.setData(newData);
      }
    }
  }

  public void readFile(File file) {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      TypeReference<LinkedHashMap<String, ListData>> typeRef = new TypeReference<LinkedHashMap<String, ListData>>() {};
      listsMap = objectMapper.readValue(file, typeRef);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public List<Map<String, String>> searchFor(String keyword) {
    List<Map<String, String>> results = new ArrayList<>();

    for (Map.Entry<String, ListData> entry : listsMap.entrySet()) {
      String listName = entry.getKey();
      ListData listData = entry.getValue();

      for (Item item : listData.getItems()) {
        if (item.search(keyword)) {
          Map<String, String> result = new HashMap<>();
          result.put("listName", listName);
          result.put("itemKey", item.getKey());
          result.put("itemData", item.getData());
          results.add(result);
        }
      }
    }
    return results;
  }

  public void updateFile() {
    File file = new File("./data/data.json");
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(listsMap);
      Files.write(file.toPath(), jsonString.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

