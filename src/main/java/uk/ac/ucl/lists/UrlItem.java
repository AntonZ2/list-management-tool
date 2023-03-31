package uk.ac.ucl.lists;

class UrlItem extends Item {

    public UrlItem(){
        // default constructor for Jackson
    }
    public UrlItem(String key, String url) {
        this.key = key;
        this.data = url;
    }

    @Override
    public boolean search(String keyword) {
        return data.toLowerCase().contains(keyword.toLowerCase()) || key.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public boolean isUrl() {
        return true;
    }
}
