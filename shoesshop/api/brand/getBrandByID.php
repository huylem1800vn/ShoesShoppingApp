<?php 
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/db.php');
    include_once('../../model/brand.php');

    $db = new db();
    $connect = $db->connect();
    $brand = new brand($connect);
    $brand->id = isset($_GET['id']) ? $_GET['id'] : die();

    $brand->show();

    $brand_item = array(
        'id' => $brand->id,
        'name' => $brand->name,
        'description' => $brand->description,
        'image' => $brand->image,
    );

    echo json_encode($brand_item);

?>