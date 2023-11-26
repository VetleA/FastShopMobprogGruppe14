package model

import com.google.gson.annotations.SerializedName

/*Gjelder alle data klassens.
  Sendt eksempel response hentet fra kassalapp apiet til https://json2kt.com/
  som konverterer JSON til kotlin data class
*/

data class PhysicalStore(
    @SerializedName("data"  ) var data  : ArrayList<StoreData> = arrayListOf(),
    @SerializedName("links" ) var links : StoreLinks?          = StoreLinks(),
    @SerializedName("meta"  ) var meta  : StoreMeta?           = StoreMeta()
)
data class Position (

    @SerializedName("lat" ) var lat : String? = null,
    @SerializedName("lng" ) var lng : String? = null

)

data class OpeningHours (

    @SerializedName("monday"    ) var monday    : String? = null,
    @SerializedName("tuesday"   ) var tuesday   : String? = null,
    @SerializedName("wednesday" ) var wednesday : String? = null,
    @SerializedName("thursday"  ) var thursday  : String? = null,
    @SerializedName("friday"    ) var friday    : String? = null,
    @SerializedName("saturday"  ) var saturday  : String? = null,
    @SerializedName("sunday"    ) var sunday    : String? = null

)


data class StoreData (

    @SerializedName("id"           ) var id           : Int?          = null,
    @SerializedName("group"        ) var group        : String?       = null,
    @SerializedName("name"         ) var name         : String?       = null,
    @SerializedName("address"      ) var address      : String?       = null,
    @SerializedName("phone"        ) var phone        : String?       = null,
    @SerializedName("email"        ) var email        : String?       = null,
    @SerializedName("fax"          ) var fax          : String?       = null,
    @SerializedName("logo"         ) var logo         : String?       = null,
    @SerializedName("website"      ) var website      : String?       = null,
    @SerializedName("detailUrl"    ) var detailUrl    : String?       = null,
    @SerializedName("position"     ) var position     : Position?     = Position(),
    @SerializedName("openingHours" ) var openingHours : OpeningHours? = OpeningHours()

)

data class StoreLinks (

    @SerializedName("first" ) var first : String? = null,
    @SerializedName("last"  ) var last  : String? = null,
    @SerializedName("prev"  ) var prev  : String? = null,
    @SerializedName("next"  ) var next  : String? = null

)
data class StoreMeta (

    @SerializedName("current_page" ) var currentPage : Int?             = null,
    @SerializedName("from"         ) var from        : Int?             = null,
    @SerializedName("last_page"    ) var lastPage    : Int?             = null,
    @SerializedName("links"        ) var links       : ArrayList<Links> = arrayListOf(),
    @SerializedName("path"         ) var path        : String?          = null,
    @SerializedName("per_page"     ) var perPage     : Int?             = null,
    @SerializedName("to"           ) var to          : Int?             = null,
    @SerializedName("total"        ) var total       : Int?             = null

)