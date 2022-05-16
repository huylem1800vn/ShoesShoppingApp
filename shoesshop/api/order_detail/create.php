<?php 
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');
    header('Access-Control-Allow-Methods: POST');
    header('Access-Control-Allow-Headers:Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

    include_once('../../config/db.php');
    include_once('../../model/order_detail.php');

    $db = new db();
    $connect = $db->connect();
    $order_detail = new order_detail($connect);

    $data = json_decode(file_get_contents("php://input"));

    $order_detail->order_id = $data->order_id;
    $order_detail->product_id = $data->product_id;
    $order_detail->size = $data->size;
    $order_detail->quantity = $data->quantity;
    $order_detail->total_amount = $data->total_amount;

    if($order_detail->create()){
        echo json_encode(array('message'=>'Order Detail Created'));
    } else {
        echo json_encode(array('message'=>'Order Detail Not Created'));
    }
?>