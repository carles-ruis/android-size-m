package com.carles.sizematters.adapter;

import com.carles.sizematters.R;

public enum DrawerOption {
    SHOES(R.string.menu_shoes, R.string.title_shoes, R.string.help_shoes, R.drawable.ic_shoes), SHIRT(
            R.string.menu_shirts, R.string.title_shirts, R.string.help_shirts, R.drawable.ic_shirt), DRESS(
            R.string.menu_dresses, R.string.title_dresses, R.string.help_dresses, R.drawable.ic_dress), JACKET(
            R.string.menu_jackets, R.string.menu_jackets, R.string.help_jackets, R.drawable.ic_jacket), TROUSERS(
            R.string.menu_trousers, R.string.title_trousers, R.string.help_trousers, R.drawable.ic_trousers), BY_GENDER(
            R.string.menu_by_gender, R.string.title_by_gender, R.string.help_by_gender, R.drawable.ic_gender);

    public final int menuId;
    public final int titleId;
    public final int helpMessageId;
    public final int iconId;

    DrawerOption(int menuId, int titleId, int helpMessageId, int iconId) {
        this.menuId = menuId;
        this.titleId = titleId;
        this.helpMessageId = helpMessageId;
        this.iconId = iconId;
    }

}
