package uk.ac.ucl.lists;

class ListLinkItem extends Item {

    public ListLinkItem(){
        // default constructor for Jackson
    }
    public ListLinkItem(String key, String listName) {
        this.key = key;
        this.data = listName;
    }

    @Override
    public boolean search(String keyword) {
        return data.toLowerCase().contains(keyword.toLowerCase()) || key.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public boolean isListLink() {
        return true;
    }
}
