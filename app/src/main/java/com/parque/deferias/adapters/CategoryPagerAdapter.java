package com.parque.deferias.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Html;

import com.parque.deferias.api.models.category.Category;
import com.parque.deferias.data.constant.AppConstant;
import com.parque.deferias.fragment.SubCategoryListFragment;

import java.util.ArrayList;

/**

 */

public class CategoryPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Category> categoryList;
    private ArrayList<Category> subCategoryList;

    public CategoryPagerAdapter(FragmentManager fm, ArrayList<Category> allCategoryList, ArrayList<Category> allSubCategoryList) {
        super(fm);
        categoryList = allCategoryList;
        subCategoryList = allSubCategoryList;
    }

    @Override
    public Fragment getItem(int i) {

        Double latestParentID = subCategoryList.get(i).getID();

        ArrayList<Category> mAllSubSubCategoryList = new ArrayList<>();
        mAllSubSubCategoryList.clear();

        for (Category category : categoryList) {
            if (latestParentID.intValue() == category.getParent().intValue()) {
                mAllSubSubCategoryList.add(category);
            }
        }

        if (mAllSubSubCategoryList.size() > 0) {
            Fragment subCategoryListFragment = new SubCategoryListFragment();
            Bundle args = new Bundle();
            args.putParcelableArrayList(AppConstant.BUNDLE_KEY_CATEGORY_LIST, categoryList);
            args.putParcelableArrayList(AppConstant.BUNDLE_KEY_SUB_CATEGORY_LIST, mAllSubSubCategoryList);
            subCategoryListFragment.setArguments(args);
            return subCategoryListFragment;

        } else {
            Fragment postListFragment = new PostListFragment();
            Bundle args = new Bundle();
            args.putInt(AppConstant.BUNDLE_KEY_CATEGORY_ID, latestParentID.intValue());
            postListFragment.setArguments(args);
            return postListFragment;
        }
    }

    @Override
    public int getCount() {
        return subCategoryList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Html.fromHtml(subCategoryList.get(position).getName());
    }

}


