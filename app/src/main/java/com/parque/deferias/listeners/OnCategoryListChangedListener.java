package com.parque.deferias.listeners;


import com.parque.deferias.api.models.category.Category;

import java.util.List;

/**

 */
public interface OnCategoryListChangedListener {
    void onListChanged(List<Category> categories);
}
