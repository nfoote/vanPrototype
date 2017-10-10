package nfoote.uhackprototype;

import android.widget.ImageView;

/**
 * Created by nfoote on 8/09/2017.
 */

public class Trucks {
    private String title, type;
    private ImageView menu;


    public Trucks(String title, String type, ImageView menu) {
        this.title = title;
        this.type = type;
        this.menu = menu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ImageView getMenu() {
        return this.menu = menu;
    }


    public void setMenu(ImageView menu) {
        this.menu = menu;
    }


}