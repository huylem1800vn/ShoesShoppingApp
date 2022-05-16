<?php 
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/db.php');
    include_once('../../model/product.php');

    $db = new db();
    $connect = $db->connect();
    $product = new product($connect);
    $product->id = isset($_GET['id']) ? $_GET['id'] : die();

    $product->show();

    $product_item = array(
        'id' => $product->id,
        'name' => $product->name,
        'price' => $product->price,
        'promotional_price' => $product->promotional_price,
        'description' => $product->description,
        'per_red' => $product->per_red,
        'image_1' => $product->image_1,
        'image_2' => $product->image_2,
        'image_3' => $product->image_3,
        'brand_id' => $product->brand_id,
        'brand_name' => $product->brand_name
    );

    echo json_encode($product_item);

?>