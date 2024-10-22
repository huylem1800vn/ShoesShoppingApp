<?php 
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/db.php');
    include_once('../../model/product.php');

    $db = new db();
    $connect = $db->connect();
    $product = new product($connect);
    $product->productID = isset($_GET['productID']) ? $_GET['productID'] : die();

    $product->show();

    $product_item = array(
        'productID' => $product->productID,
        'name' => $product->name,
        'price' => $product->price,
        'promotionalPrice' => $product->promotionalPrice,
        'description' => $product->description,
        'perRed' => $product->perRed,
        'image1' => $product->image1,
        'image2' => $product->image2,
        'image3' => $product->image3,
        'image3' => $product->image3,
        'image4' => $product->image4,
        'image5' => $product->image5,
        'brandID' => $product->brandID,
        'brandName' => $product->brandName,
        'categoryID' => $product->categoryID,
        'categoryName' => $product->categoryName
    );

    echo json_encode($product_item);

?>