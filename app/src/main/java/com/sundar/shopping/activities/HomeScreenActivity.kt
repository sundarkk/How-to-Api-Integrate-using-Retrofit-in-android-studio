package com.sundar.shopping.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.JsonObject
import com.sundar.shopping.R
import com.sundar.shopping.adapter.*
import com.sundar.shopping.classes.ApiServices
import com.sundar.shopping.classes.AppBaseActivity
import com.sundar.shopping.classes.HelperClass
import com.sundar.shopping.custom_classes.GridSpacingItemDecoration
import com.sundar.shopping.interfaces.ApiInterface
import kotlinx.android.synthetic.main.activity_home_screen.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class HomeScreenActivity : AppBaseActivity() {
    private val TAG = "HomeScreenActivityTag"
    private val activity = this@HomeScreenActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        init()
    }

    private fun init() {

        //Todo Home Api call
        home(3)


    }

    private fun showContentView() {
        content_view.visibility = View.VISIBLE
        progress_loader.visibility = View.GONE
    }

    private fun showLoader() {
        content_view.visibility = View.GONE
        progress_loader.visibility = View.VISIBLE
    }


    private fun home(
        // int: page_count
        user_id: Int
    ) {

        // Log.d("tjCheck", "page_count list: " + page_count)
        if (HelperClass.getNetworkInfo(activity)) {
            val apiInterface = ApiServices.apiService(activity).create(ApiInterface::class.java)
            val call = apiInterface.getHome(3)
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(
                    call: Call<JsonObject>,
                    response: Response<JsonObject>
                ) {
                    if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {

                    } else if (response.code() == HttpURLConnection.HTTP_OK) {
                        showContentView()
                        // val result = JSONObject(response.body().toString())
                        val body = JSONObject(response.body().toString())
                        if (body.getBoolean("status") == true) {

                            val result = body.getJSONObject("result")
                            val currentCategoriesList =
                                body.getJSONObject("result").getJSONArray("categories")
                            Log.d(TAG, "categories_data" + currentCategoriesList.toString())
                            Log.d(TAG, "result_data" + result.toString())

                            val finalCategoriesList = JSONArray()
                            for (i in 0 until currentCategoriesList.length() + 1) {
                                if (i == 0) {
                                    val zeroIndexData = JSONObject()
                                    zeroIndexData.put("category_name", "All")
                                    zeroIndexData.put("image", "")
                                    finalCategoriesList.put(zeroIndexData)
                                } else {
                                    finalCategoriesList.put(
                                        currentCategoriesList.getJSONObject(
                                            (i - 1)
                                        )
                                    )
                                }
                            }

                            //section_one
                            val bannerSectionOne = result.getJSONArray("section_one")
                            Log.d(TAG, "sundar_banner" + bannerSectionOne)
                            recyclerview_price_off.adapter =
                                priceOffAdapter(activity, bannerSectionOne);
                            first_banner_indicator.setRecyclerView(recyclerview_price_off)


                            val bannerSectionFour = result.getJSONArray("section_four")
                            Log.d(TAG, "item_view_banner" + bannerSectionFour)
                            offer_recyclerview.adapter =
                                offerAdapter(activity, bannerSectionFour)
                            indicator_offer.attachToRecyclerView(offer_recyclerview)

                            val bannerSectionTwo = result.getJSONArray("section_two")
                            Log.d(TAG, "scheck $bannerSectionTwo")



                            arrival_recyclerview.adapter =
                                arrivalAdapter(activity, bannerSectionTwo);
                            // indicator.setRecyclerView(offer_recyclerview)

                            val bannerSectionThree = result.getJSONArray("section_three")
                            Log.d(TAG, "tija_banner" + bannerSectionThree)

                            //Todo layout visibility gone/visisble
                            if (bannerSectionThree.isNull(0)) {
                                layout_deal.visibility = View.GONE
                            } else {
                                layout_deal.visibility = View.VISIBLE
                                recyclerview_deal_off_day.adapter =
                                    dealOffDayAdapter(activity, bannerSectionThree)

                                recyclerview_deal_off_day.addItemDecoration(
                                    GridSpacingItemDecoration(2, 20, false)
                                )

                                arrival_text.text = result.getString("section_two_title")
                                deal_of_text.text = result.getString("section_three_title")
                            }

                            //Todo snaphelper data
                            /*val snapHelper: SnapHelper = PagerSnapHelper()
                        snapHelper.attachToRecyclerView(recyclerview_categories)*/

                            recyclerview_categories.adapter =
                                categories_Adapter(activity, finalCategoriesList);

                            val recentViewedItems = result.getJSONArray("recent_viewed_items")
                            Log.d(TAG, "sCheck_recent_viewed_items: $recentViewedItems")
                            item_view_recyclerview.adapter =
                                itemsViewAdapter(activity, recentViewedItems)

                            //Todo recentView data (Image arrray)


                        } else {

//
                        }

                    }

                    Log.d(TAG, "Resopnse of home api: " + response.body().toString())
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                    //   Handler().postDelayed(Runnable { enableOrientation() }, 1000)
//                    HelperClass.hideLoader()
                    HelperClass.snackbar(
                        activity,
                        main_layout,
                        getString(R.string.something_went_wrong)
                    )
                }

            })


        } else {
            //no network
            HelperClass.snackbar(
                activity,
                main_layout,
                getString(R.string.check_your_internet_connection)
            )

        }

    }
}