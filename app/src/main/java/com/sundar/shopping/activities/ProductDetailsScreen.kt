package com.sundar.shopping.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.sundar.shopping.R
import com.sundar.shopping.classes.ApiServices
import com.sundar.shopping.classes.AppBaseActivity
import com.sundar.shopping.classes.Constants
import com.sundar.shopping.classes.HelperClass
import com.sundar.shopping.interfaces.ApiInterface

import com.google.gson.JsonObject
import com.sundar.shopping.adapter.ProductDetailsImageAdapter
import com.sundar.shopping.adapter.RatingAdapter
import kotlinx.android.synthetic.main.activity_product_details_screen.*
import kotlinx.android.synthetic.main.activity_product_details_screen.content_view
import kotlinx.android.synthetic.main.activity_product_details_screen.main_layout
import kotlinx.android.synthetic.main.activity_product_details_screen.progress_loader

import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.util.*


class ProductDetailsScreen : AppBaseActivity() {
    private val TAG = "ProductDetailsScreen"
    private val activity = this@ProductDetailsScreen
    var product_ID = ""
    var color = ""
    var quantity = ""
    var size = ""
    var colorID = /*Int*/ ""
    var quantityID = /*Int*/ ""
    var sizeID = /*Int*/ ""
    var colorApiId = ""
    var sizeApiId = ""
    private var allSizesToShow: MutableList<String> = ArrayList()
    private var allColorToShow: MutableList<String> = ArrayList()
    private var productSelectedSizePosition = 0
    private var productSelectedColorPosition = 0
    private var allSizes: JSONArray? = null
    private var allColors: JSONArray? = null
    private var selectedProductColor = 0
    private var selectedProductSize = 0


    //  var

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details_screen)
        init()


    }

    private fun init() {

        var allimages = intent.getStringExtra(Constants.MULTIPLEIMAGES);
        Log.d(TAG, "img:  " + allimages.toString())

        back_icon.setOnClickListener {
            finish()
        }
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(products_image_recyclerview)
        products_image_recyclerview.adapter =
            ProductDetailsImageAdapter(activity, JSONArray(allimages))
        indicator.setRecyclerView(products_image_recyclerview)
        ratinbar_recyclerview.adapter = RatingAdapter(activity)

        //bottom_layout

        if (nesteScroll_productDetails != null) {
            nesteScroll_productDetails.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if (scrollY > oldScrollY) {
                    Log.i(TAG, "Scroll DOWN")
                    bottom_layout.visibility = View.GONE
                    bottom_layout_two.visibility = View.VISIBLE
                }
                if (scrollY < oldScrollY) {
                    Log.i(TAG, "Scroll UP")
                    bottom_layout.visibility = View.GONE

                }
                if (scrollY == 0) {
                    Log.i(TAG, "TOP SCROLL")
                    bottom_layout.visibility = View.VISIBLE
                }
                if (scrollY == v.measuredHeight - v.getChildAt(0).measuredHeight) {
                    Log.i(TAG, "BOTTOM SCROLL")
                    bottom_layout.visibility = View.GONE
                    bottom_layout_two.visibility = View.VISIBLE
                }
            })
        }


        //Todo color select spinner
        val colorAdater = ArrayAdapter.createFromResource(
            this,
            R.array.color_items,
            android.R.layout.simple_spinner_item
        )
        colorAdater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        color_spinner.adapter = colorAdater
        color_spinner.onItemClickListener

        //Todo color spinner work.
        color_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {


                //todo   working
                val text: String = parent?.getItemAtPosition(position).toString()
                color_details.text = text
//
            } // to close the onItemSelected

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
        color_details.setOnClickListener {
            color_spinner.performClick()
        }

        allSizesToShow.add("Select your size:")

        val dataAdapter =
            ArrayAdapter(
                activity,
                android.R.layout.simple_spinner_item,
                allSizesToShow
            )

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        size_spinner.adapter = dataAdapter

      /*  size_spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.d("tag", "Nothing selected")
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    productSelectedSizePosition = position

                    text_size.text = allSizes?.getJSONObject(position - 1)?.getString("size")
                }
            }*/

        buy_now.setOnClickListener {
            startActivity(Intent(activity, AddressListActivity::class.java))
            //  finish()
        }
        buy_now_two.setOnClickListener {
            buy_now.performClick()

        }

        decrement.setOnClickListener {
            display(text_quantity.text.toString().toInt() - 1)

        }

        increment.setOnClickListener {

            display(text_quantity.text.toString().toInt() + 1)

        }


        more_layout.setOnClickListener {
            startActivity(Intent(activity, RatingReviewsScreen::class.java))
            finish()
        }
        var product_ID = ""
        product_ID = intent.getStringExtra(Constants.PRODUCT_ID).toString()
        Log.d(TAG, "lado id: " + product_ID)
        //  productDetails(product_id = product_ID.toInt())
        try {

            productDetails(product_ID.toString().toInt())

        } catch (e: Exception) {
            e.printStackTrace()
        }
        //Todo add to cart buttton work
        add_to_cart_two.setOnClickListener {
            add_to_cart.performClick()
        }

        add_to_cart.setOnClickListener {
            if (text_quantity == null) {
                HelperClass.snackbar(activity, main_layout, getString(R.string.select_quantity))

            } else if (size_spinner == null) {
                //  HelperClass.snackbar(activity, main_layout, getString(R.string.select_size))
            } else if (color_spinner == null) {
                HelperClass.snackbar(activity, main_layout, getString(R.string.select_size))
            } else {
                var id = 119
                //  var quantity = 2
                addToCart(
                    HelperClass.getProductID(activity).toString().toInt(),

                    id,
                    quantity,
                    sizeID,
                    colorID
                )

                Log.d(TAG, "product_id: $quantity-$size-$color")

            }

        }
        whishlist_icon.setOnClickListener {

            addToWishlist(1, HelperClass.getProductID(activity).toString().toInt())
        }

        colors(0)
        sizes(0)
    }


    private fun display(number: Int) {

        if (number >= 1) {
            text_quantity.setText("$number")
        }
        //  text_quantity.setText("$number")
    }

    //Todo Loader visiblity
    private fun showContentView() {
        content_view.visibility = View.VISIBLE
        progress_loader.visibility = View.GONE
    }

    private fun showLoader() {
        content_view.visibility = View.GONE
        progress_loader.visibility = View.VISIBLE
    }


    private fun productDetails(product_id: Int) {

        Log.d("sundar_Check", "product ID:  " + product_id)
        if (HelperClass.getNetworkInfo(activity)) {
            val apiInterface = ApiServices.apiService(activity).create(ApiInterface::class.java)
            val call = apiInterface.getProductDetails(product_id)
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {

                    } else if (response.code() == HttpURLConnection.HTTP_OK) {
                        showContentView()
                        // val result = JSONObject(response.body().toString())
                        val body = JSONObject(response.body().toString())
                        if (body.getBoolean("status") == true) {

                            val result = body.getJSONObject("result");
                            Log.d(TAG, "products_data" + result)

                            tittle.text = result.getString("title")
                            first_price.text = "$" + result.getString("discounted_price")
                            second_price.text = "$" + result.getString("price")
                            discount_price.text = result.getString("discount") + " " + "OFF"
                            sub_tittle.text = result.getString("description")
                            quantity = result.getString("quantity")


                            text_quantity.text = quantity


                            val ProductID = result.getString("id")
                            HelperClass.setProductID(ProductID, activity)

                            val colorObject = result.getJSONObject("color")
                            color = colorObject.getString("colour")

                            colorID = result.getString("id").toString().toInt().toString()
                            Log.d(TAG, "color_Id: $colorID")


                            val sizeObject = result.getJSONObject("size")
                            size = sizeObject.getString("size")
                            sizeID = sizeObject.getString("id").toString().toInt().toString()


                            Log.d(TAG, "allDta_check: $color-$product_ID-$quantity-$size")

                            Log.d(TAG, "scheck_userId: $ProductID}")


                        } else {

//
                        }

                    }

                    Log.d(TAG, "Resopnse of product_details api: " + response.body().toString())
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                    //   Handler().postDelayed(Runnable { enableOrientation() }, 1000)
                    HelperClass.hideLoader()
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

    private fun addToCart(
        product_id: Int,
        user_id: Int,
        quantity: String,
        size_id: String,
        color_id: String
    ) {
        Log.d("sundar_Check", "product ID:  " + product_id)
        if (HelperClass.getNetworkInfo(activity)) {
            val apiInterface = ApiServices.apiService(activity).create(ApiInterface::class.java)
            val call = apiInterface.getAddToCart(product_id, user_id, quantity, size_id, color_id)
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {

                    } else if (response.code() == HttpURLConnection.HTTP_OK) {
                        showContentView()
                        // val result = JSONObject(response.body().toString())
                        val body = JSONObject(response.body().toString())
                        if (body.getBoolean("status") == true) {

                            /*val result = body.getJSONObject("result");
                            Log.d(TAG, "products_data" + result)*/

                            HelperClass.showSimpleAlertDialog(
                                activity,
                                /*getString(R.string.item_added_to_cart),*/
                                body.getString("message"),
                                "Ok",
                                true,
                                false
                            )

                            //  add_to_cart.setText(activity.getString(R.string.go_to_cart)) //in underdvl

                            /*startActivity(Intent(activity, MyCartActivity::class.java))
                            finish()*/


                        } else if (body.getBoolean("status") == false) {

                            HelperClass.showSimpleAlertDialog(
                                activity,
                                getString(R.string.item_already_added_in_the_cart),
                                /*   body.getString("result"),*/
                                "Ok",
                                true,
                                false
                            )
//
                        }

                    }

                    Log.d(TAG, "Resopnse of addToCart api: " + response.body().toString())
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                    //   Handler().postDelayed(Runnable { enableOrientation() }, 1000)
                    HelperClass.hideLoader()
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


    private fun addToWishlist(user_id: Int, product_id: Int) {

        Log.d("sundar_Check", "product ID:  " + product_id)
        if (HelperClass.getNetworkInfo(activity)) {
            val apiInterface = ApiServices.apiService(activity).create(ApiInterface::class.java)
            val call = apiInterface.getAddToWishlist(user_id, product_id)
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {

                    } else if (response.code() == HttpURLConnection.HTTP_OK) {
                        showContentView()
                        // val result = JSONObject(response.body().toString())
                        val body = JSONObject(response.body().toString())
                        if (body.getBoolean("status") == true) {
                            whishlist_icon.setImageResource(R.drawable.red_heart)
                            //  whishlist_icon.setColorFilter(getResources().getColor(R.color.red), android.graphics.PorterDuff.Mode.MULTIPLY)
                            val result = body.getJSONObject("result");
                            Log.d(TAG, "products_data" + result)


                            HelperClass.showSimpleAlertDialog(
                                activity,
                                body.getString("message"),
                                "Ok",
                                true,
                                false
                            )

                            //   Log.d(TAG, "allDta_check: $color-$product_ID-$quantity-$size")

                            // Log.d(TAG, "scheck_userId: $ProductID}")


                        } else {
                            whishlist_icon.setColorFilter(
                                getResources().getColor(R.color.black),
                                android.graphics.PorterDuff.Mode.MULTIPLY
                            )

//
                        }

                    }

                    Log.d(TAG, "Resopnse of addToWishlist api: " + response.body().toString())
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                    //   Handler().postDelayed(Runnable { enableOrientation() }, 1000)
                    HelperClass.hideLoader()
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

    fun colors(page_count: Int) {
        if (HelperClass.getNetworkInfo(activity)) {
            val apiInterface = ApiServices.apiService(activity).create(ApiInterface::class.java)
            val call = apiInterface.getColors()
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    } else if (response.code() == HttpURLConnection.HTTP_OK) {
                        showContentView()
                        // val result = JSONObject(response.body().toString())
                        val body = JSONObject(response.body().toString())
                        if (body.getBoolean("status") == false) {

                            allColors = body.getJSONArray("result")
                            Log.d(TAG, "allColors_check: $allColors")


                        } else {

//
                        }

                    }

                    Log.d(TAG, "Resopnse of color api: " + response.body().toString())
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                    //   Handler().postDelayed(Runnable { enableOrientation() }, 1000)
                    HelperClass.hideLoader()
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

    fun sizes(page_count: Int) {
        if (HelperClass.getNetworkInfo(activity)) {
            val apiInterface = ApiServices.apiService(activity).create(ApiInterface::class.java)
            val call = apiInterface.getSizes()
            call.enqueue(object : Callback<JsonObject> {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                    } else if (response.code() == HttpURLConnection.HTTP_OK) {
                        showContentView()
                        // val result = JSONObject(response.body().toString())
                        val body = JSONObject(response.body().toString())
                        if (body.getBoolean("status") == false) {

                            val result = body.getJSONArray("result")

                            allSizes = body.getJSONArray("result")

                            Log.d(TAG, "allsizes_check: $allSizes")

                            for (i in 0 until allSizes!!.length()) {
                                val current = allSizes!!.getJSONObject(i)

                                Log.d(TAG, "currnt_sizes: $current")

                         allSizesToShow.add(current.getString("size"))

                                val sizeData = current.getString("size")
                             //   Log.d(TAG, "check_size: $sizeData -$allSizesToShow")
                                Log.d(TAG, "check_sizes: $allSizesToShow")

                                // text_size.text = sizeData


                            }

                            /// sizeResultArray = result.toString()


                            Log.d(TAG, "sk_check: $result")
                            /* for (i in 0 until result.length()) {
                                 sizeID = result.getString(i)
                                 Log.d(TAG, "size_check: $sizeID")
                             }*/

                            /*sizeApiId = result.getJSONObject(0) as Nothing?
                            Log.d(TAG, "sizeApiId_check: $sizeApiId")*/


                        } else {

//
                        }

                    }

                    Log.d(TAG, "Resopnse of size api: " + response.body().toString())
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {

                    //   Handler().postDelayed(Runnable { enableOrientation() }, 1000)
                    HelperClass.hideLoader()
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


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }


}