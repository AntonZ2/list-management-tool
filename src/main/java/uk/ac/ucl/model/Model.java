package uk.ac.ucl.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.ucl.lists.Item;
import uk.ac.ucl.lists.ListData;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.nio.file.Files;
import java.util.AbstractMap;

public class Model {
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
      updateListReferences(oldName, newName);
      updateFile();
    }
  }

  public void updateListReferences(String oldListName, String newListName) {
    for (ListData listData : listsMap.values()) {
      for (Item item : listData.getItems()) {
        if (item.isListLink() && item.getData().equals(oldListName)) {
          item.setData(newListName);
        }
      }
    }
  }


  public void addItem(String listName, String itemType, String itemKey, String itemData) {
    Item newItem = Item.createItem(itemType, itemKey, itemData);
    ListData list = getList(listName);
    if (list != null) {
      list.addItem(newItem);
      updateFile();
    }
  }

  public void deleteItem(String listName, int itemInd) {
    ListData list = getList(listName);
    if (list != null) {
      list.removeItem(itemInd);
      updateFile();
    }
  }

  public void updateItem(String listName, int itemInd, String newData) {
    ListData list = getList(listName);
    if (list != null) {
      Item item = list.getItem(itemInd);
      if (item != null) {
        item.setData(newData);
        updateFile();
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


  public List<AbstractMap.SimpleEntry<String, Item>> searchFor(String keyword) {
    List<AbstractMap.SimpleEntry<String, Item>> results = new ArrayList<>();

    for (Map.Entry<String, ListData> entry : listsMap.entrySet()) {
      String listName = entry.getKey();
      ListData list = entry.getValue();
      for (Item item : list.getItems()) {
        if (item.search(keyword)) {
          AbstractMap.SimpleEntry<String, Item> result = new AbstractMap.SimpleEntry<>(listName, item);
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

