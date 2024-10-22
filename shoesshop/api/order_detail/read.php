<?php 
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/db.php');
    include_once('../../model/order_detail.php');

    $db = new db();
    $connect = $db->connect();
    $orderdetail = new orderdetail($connect);
    $read = $orderdetail->read();

    $num = $read->rowCount();

    if($num > 0){
        $array_order_detail = [];

        while($row = $read->fetch(PDO::FETCH_ASSOC)){
            
            extract($row);

            $order_detail_item = array(
                'orderDetailID' => $orderDetailID,
                'orderID' => $orderID,
                'productID' => $productID,
                'size' => $size,
                'quantity' => $quantity,
                'totalAmount' => $totalAmount,
                'productName' => $productName,
                'image' => $image,
                'productPrice' => $productPrice,
            );
            array_push($array_order_detail, $order_detail_item);
        }
        echo json_encode($array_order_detail);
    }
?>