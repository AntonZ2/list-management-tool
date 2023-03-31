package uk.ac.ucl.lists;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TextItem.class, name = "text"),
        @JsonSubTypes.Type(value = UrlItem.class, name = "url"),
        @JsonSubTypes.Type(value = ImageItem.class, name = "image"),
        @JsonSubTypes.Type(value = ListLinkItem.class, name = "list_link")
})

public abstract class Item {
    protected String key;
    protected String data;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public abstract boolean search(String keyword);

    @JsonIgnore
    public boolean isImage() {
        return false;
    }

    @JsonIgnore
    public boolean isUrl() {
        return false;
    }

    @JsonIgnore
    public boolean isListLink() {
        return false;
    }

    @JsonIgnore
    public static Item createItem(String itemType, String itemKey, String itemData) {
        switch (itemType) {
            case "text":
                return new TextItem(itemKey, itemData);
            case "url":
                return new UrlItem(itemKey, itemData);
            case "image":
                return new ImageItem(itemKey, itemData);
            case "list_link":
                return new ListLinkItem(itemKey, itemData);
            default:
                throw new IllegalArgumentException("Invalid item type: " + itemType);
        }
    }
}
