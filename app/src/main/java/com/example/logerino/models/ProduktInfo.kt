package model

import com.google.gson.annotations.SerializedName

/*Gjelder alle data klassens.
  Sendt eksempel response hentet fra kassalapp apiet til https://json2kt.com/
  som konverterer JSON til kotlin data class
*/

data class ProduktInfo(
    @SerializedName("data"  ) var data  : ArrayList<Data> = arrayListOf(),
    @SerializedName("links" ) var links : Links?          = Links(),
    @SerializedName("meta"  ) var meta  : Meta?           = Meta()
)
data class Data (

    @SerializedName("id"                 ) var id               : Int?                    = null,
    @SerializedName("name"               ) var name             : String?                 = null,
    @SerializedName("brand"              ) var brand            : String?                 = null,
    @SerializedName("vendor"             ) var vendor           : String?                 = null,
    @SerializedName("ean"                ) var ean              : String?                 = null,
    @SerializedName("url"                ) var url              : String?                 = null,
    @SerializedName("image"              ) var image            : String?                 = null,
    @SerializedName("description"        ) var description      : String?                 = null,
    @SerializedName("ingredients"        ) var ingredients      : String?                 = null,
    @SerializedName("current_price"      ) var currentPrice     : Double?                 = null,
    @SerializedName("current_unit_price" ) var currentUnitPrice : Double?                 = null,
    @SerializedName("weight"             ) var weight           : Int?                    = null,
    @SerializedName("weight_unit"        ) var weightUnit       : String?                 = null,
    @SerializedName("store"              ) var store            : Store?                  = Store(),
    @SerializedName("price_history"      ) var priceHistory     : ArrayList<PriceHistory> = arrayListOf(),
    @SerializedName("allergens"          ) var allergens        : ArrayList<Allergens>    = arrayListOf(),
    @SerializedName("nutrition"          ) var nutrition        : ArrayList<Nutrition>    = arrayListOf(),
    @SerializedName("created_at"         ) var createdAt        : String?                 = null,
    @SerializedName("updated_at"         ) var updatedAt        : String?                 = null

)

data class Links (

    @SerializedName("first" ) var first : String? = null,
    @SerializedName("last"  ) var last  : String? = null,
    @SerializedName("prev"  ) var prev  : String? = null,
    @SerializedName("next"  ) var next  : String? = null

)
data class Nutrition (

    @SerializedName("code"         ) var code        : String? = null,
    @SerializedName("display_name" ) var displayName : String? = null,
    @SerializedName("amount"       ) var amount      : Double? = null,
    @SerializedName("unit"         ) var unit        : String? = null

)
data class Allergens (

    @SerializedName("code"         ) var code        : String? = null,
    @SerializedName("display_name" ) var displayName : String? = null,
    @SerializedName("contains"     ) var contains    : String? = null

)

data class PriceHistory (

    @SerializedName("price" ) var price : Double? = null,
    @SerializedName("date"  ) var date  : String? = null

)

data class Meta (

    @SerializedName("current_page" ) var currentPage : Int?    = null,
    @SerializedName("from"         ) var from        : Int?    = null,
    @SerializedName("path"         ) var path        : String? = null,
    @SerializedName("per_page"     ) var perPage     : Int?    = null,
    @SerializedName("to"           ) var to          : Int?    = null

)
data class Store (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("code" ) var code : String? = null,
    @SerializedName("url"  ) var url  : String? = null,
    @SerializedName("logo" ) var logo : String? = null

)