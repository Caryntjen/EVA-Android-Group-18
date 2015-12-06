package com.evavzw.twentyonedayschallenge.tabfragments;

/*
    This interface needs to be implemented by ever Fragments that is used in the tabs to update the information when swiped to.
*/
public interface ITabFragment {
    /*
        When the fragment is swiped or clicked to, the information needs to be updated, which happens here.
    */
    void updateFragment();
}
