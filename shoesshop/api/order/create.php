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

    $order->orderID = $data->orderID;
    $order->customerName = $data->customerName;
    $order->customerAddress = $data->customerAddress;
    $order->customerPhone = $data->customerPhone;
    $order->orderDate = $data->orderDate;
    $order->createDate = $data->orderDate;
    $order->totalAmount = $data->totalAmount;
    $order->deliveryDate =$data->orderDate;
    $order->createUser = '1';
    $order->confirmUser = '';
    $order->shipper = '';
    $order->orderStatus = 1;
    // $order->createDate = $data->createDate;
    // $order->totalAmount = $data->totalAmount;
    // $order->deliveryDate = $data->deliveryDate;
    // $order->createUser = $data->createUser;
    // $order->confirmUser = $data->confirmUser;
    // $order->shipper = $data->shipper;
    // $order->orderStatus = $data->orderStatus;

    if($order->create()){
        echo json_encode(array('message'=>'Order Created'));
    } else {
        echo json_encode(array('message'=>'Order Not Created'));
    }
?>