<?php 
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');
    header('Access-Control-Allow-Methods: POST');
    header('Access-Control-Allow-Headers:Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

    include_once('../../config/db.php');
    include_once('../../model/brand.php');

    $db = new db();
    $connect = $db->connect();
    $brand = new brand($connect);

    $data = json_decode(file_get_contents("php://input"));

    $brand->brandID = $data->brandID;
    $brand->description = $data->description;
    $brand->image = $data->image;

    if($brand->create()){
        echo json_encode(array('message'=>'Brand Created'));
    } else {
        echo json_encode(array('message'=>'Brand Not Created'));
    }
?>