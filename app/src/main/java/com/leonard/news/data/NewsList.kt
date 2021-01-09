package com.leonard.news.data

data class NewsList(
	val displayName: String? = null,
	val relatedImages: List<Any>? = null,
	val sponsored: Boolean? = null,
	val url: String? = null,
	val assetType: String? = null,
	val timeStamp: Long? = null,
	val onTime: Long? = null,
	val assets: List<AssetsItem>? = null,
	val relatedAssets: List<Any>? = null,
	val id: Int? = null,
	val categories: List<Any>? = null,
	val lastModified: Long? = null,
	val authors: List<Any>? = null
)

data class AuthorsItem(
	val relatedAssets: List<Any>? = null,
	val relatedImages: List<RelatedImagesItem>? = null,
	val name: String? = null,
	val title: String? = null,
	val email: String? = null
)

data class AssetsItem(
	val byLine: String? = null,
	val acceptComments: Boolean? = null,
	val sources: List<SourcesItem>? = null,
	val relatedImages: List<RelatedImagesItem>? = null,
	val theAbstract: String? = null,
	val legalStatus: String? = null,
	val sponsored: Boolean? = null,
	val overrides: Overrides? = null,
	val url: String,
	val numberOfComments: Int? = null,
	val assetType: String? = null,
	val timeStamp: Long? = null,
	val companies: List<Any>? = null,
	val relatedAssets: List<RelatedAssetsItem>? = null,
	val id: Int? = null,
	val categories: List<CategoriesItem>? = null,
	val lastModified: Long? = null,
	val tabletHeadline: String? = null,
	val indexHeadline: String? = null,
	val headline: String? = null,
	val authors: List<Any?>? = null,
	val signPost: String? = null,
	val onTime: Long? = null
)

data class RelatedAssetsItem(
	val timeStamp: Long? = null,
	val sponsored: Boolean? = null,
	val id: Int? = null,
	val categories: List<CategoriesItem>? = null,
	val lastModified: Long? = null,
	val headline: String? = null,
	val url: String? = null,
	val authors: List<Any>? = null,
	val assetType: String? = null
)

data class Overrides(
	val overrideAbstract: String? = null,
	val overrideHeadline: String? = null
)

data class CategoriesItem(
	val name: String? = null,
	val orderNum: Int? = null,
	val sectionPath: String? = null
)

data class RelatedImagesItem(
	val thumbnail2x: String? = null,
	val thumbnail: String? = null,
	val brands: List<Any>? = null,
	val large: String? = null,
	val description: String? = null,
	val sponsored: Boolean? = null,
	val type: String? = null,
	val url: String,
	val assetType: String? = null,
	val timeStamp: Long? = null,
	val large2x: String? = null,
	val width: Int,
	val photographer: String? = null,
	val id: Int? = null,
	val categories: List<Any>? = null,
	val lastModified: Long? = null,
	val authors: List<Any>? = null,
	val height: Int,
	val xLarge: String? = null,
	val xLarge2x: String? = null
)

data class SourcesItem(
	val tagId: String? = null
)

data class CompaniesItem(
	val companyCode: String? = null,
	val companyNumber: String? = null,
	val companyName: String? = null,
	val exchange: String? = null,
	val id: Int? = null,
	val abbreviatedName: String? = null
)

