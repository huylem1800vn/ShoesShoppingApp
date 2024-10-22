<?php 
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');
    header('Access-Control-Allow-Methods: POST');
    header('Access-Control-Allow-Headers:Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

    include_once('../../config/db.php');
    include_once('../../model/order_detail.php');

    $db = new db();
    $connect = $db->connect();
    $orderdetail = new orderdetail($connect);

    $data = json_decode(file_get_contents("php://input"));

    $orderdetail->orderID = $data->orderID;
    $orderdetail->productID = $data->productID;
    $orderdetail->size = $data->size;
    $orderdetail->quantity = $data->quantity;
    $orderdetail->totalAmount = $data->totalAmount;

    if($orderdetail->create()){
        echo json_encode(array('message'=>'Order Detail Created'));
    } else {
        echo json_encode(array('message'=>'Order Detail Not Created'));
    }
?>