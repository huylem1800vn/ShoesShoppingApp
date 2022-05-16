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
                'id' => $id,
                'customer_name' => $customer_name,
                'customer_address' => $customer_address,
                'customer_phone' => $customer_phone,
                'total_amount' => $total_amount,
                'order_date' => $order_date,
            );
            array_push($array_order, $order_item);
        }
        echo json_encode($array_order);
    }
?>