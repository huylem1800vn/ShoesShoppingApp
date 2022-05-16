<?php 
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');
    header('Access-Control-Allow-Methods: POST');
    header('Access-Control-Allow-Headers:Access-Control-Allow-Headers, Content-Type, Access-Control-Allow-Methods, Authorization, X-Requested-With');

    include_once('../../config/db.php');
    include_once('../../model/order.php');

    $db = new db();
    $connect = $db->connect();
    $order = new order($connect);

    $data = json_decode(file_get_contents("php://input"));

    $order->id = $data->id;
    $order->customer_name = $data->customer_name;
    $order->customer_address = $data->customer_address;
    $order->customer_phone = $data->customer_phone;
    $order->total_amount = $data->total_amount;
    $order->order_date = $data->order_date;

    if($order->create()){
        echo json_encode(array('message'=>'Order Created'));
    } else {
        echo json_encode(array('message'=>'Order Not Created'));
    }
?>