// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: product/product.proto

// Protobuf Java Version: 3.25.5
package com.gaboot.gabshop.grpc.product;

public interface ProductOrBuilder extends
    // @@protoc_insertion_point(interface_extends:Product)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 id = 1;</code>
   * @return The id.
   */
  long getId();

  /**
   * <code>string name = 2;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 2;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>string sku = 3;</code>
   * @return The sku.
   */
  java.lang.String getSku();
  /**
   * <code>string sku = 3;</code>
   * @return The bytes for sku.
   */
  com.google.protobuf.ByteString
      getSkuBytes();

  /**
   * <code>string unit = 4;</code>
   * @return The unit.
   */
  java.lang.String getUnit();
  /**
   * <code>string unit = 4;</code>
   * @return The bytes for unit.
   */
  com.google.protobuf.ByteString
      getUnitBytes();

  /**
   * <code>double price = 5;</code>
   * @return The price.
   */
  double getPrice();

  /**
   * <code>double stock = 6;</code>
   * @return The stock.
   */
  double getStock();

  /**
   * <code>string default_image = 7;</code>
   * @return The defaultImage.
   */
  java.lang.String getDefaultImage();
  /**
   * <code>string default_image = 7;</code>
   * @return The bytes for defaultImage.
   */
  com.google.protobuf.ByteString
      getDefaultImageBytes();

  /**
   * <code>string default_image_thumb = 8;</code>
   * @return The defaultImageThumb.
   */
  java.lang.String getDefaultImageThumb();
  /**
   * <code>string default_image_thumb = 8;</code>
   * @return The bytes for defaultImageThumb.
   */
  com.google.protobuf.ByteString
      getDefaultImageThumbBytes();

  /**
   * <code>int64 created_at = 9;</code>
   * @return The createdAt.
   */
  long getCreatedAt();

  /**
   * <code>int64 updated_at = 10;</code>
   * @return The updatedAt.
   */
  long getUpdatedAt();

  /**
   * <code>bool is_active = 11;</code>
   * @return The isActive.
   */
  boolean getIsActive();
}
