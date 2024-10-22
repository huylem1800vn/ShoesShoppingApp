<?php 
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/db.php');
    include_once('../../model/order.php');

    $db = new db();
    $connect = $db->connect();
    $order = new order($connect);
    $read = $order->read();

    $num = $read->rowCount();

    if($num > 0){
        $array_order = [];

        while($row = $read->fetch(PDO::FETCH_ASSOC)){
            
            extract($row);

            $order_item = array(
                'orderID' => $orderID,
                'customerName' => $customerName,
                'customerAddress' => $customerAddress,
                'customerPhone' => $customerPhone,
                'createDate' => $createDate,
                'orderDate' => $orderDate,
                'totalAmount' => $totalAmount,
                'deliveryDate' => $deliveryDate,
                'createUser' => $createUser,
                'confirmUser' => $confirmUser,
                'shipper' => $shipper,
                'orderStatus' => $orderStatus,
                'orderStatusName' => $orderStatusName,
                'shipperName' => $shipperName,
                'confirmUserName' => $confirmUserName,
                'createUserName' => $createUserName
            );
            array_push($array_order, $order_item);
        }
        echo json_encode($array_order);
    }
?>