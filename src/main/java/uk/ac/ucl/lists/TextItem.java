package uk.ac.ucl.lists;

class TextItem extends Item {

    public TextItem(){
        // default constructor for Jackson
    }
    public TextItem(String key, String text) {
        this.key = key;
        this.data = text;
    }

    @Override
    public boolean search(String keyword) {
        return data.toLowerCase().contains(keyword.toLowerCase()) || key.toLowerCase().contains(keyword.toLowerCase());
    }
}
