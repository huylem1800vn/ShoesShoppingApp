<?php 
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/db.php');
    include_once('../../model/order_detail.php');

    $db = new db();
    $connect = $db->connect();
    $order_detail = new order_detail($connect);
    $read = $order_detail->read();

    $num = $read->rowCount();

    if($num > 0){
        $array_order_detail = [];

        while($row = $read->fetch(PDO::FETCH_ASSOC)){
            
            extract($row);

            $order_detail_item = array(
                'id' => $id,
                'order_id' => $order_id,
                'product_id' => $product_id,
                'size' => $size,
                'quantity' => $quantity,
                'total_amount' => $total_amount,
                'product_name' => $product_name,
                'image' => $image,
                'product_price' => $product_price,
            );
            array_push($array_order_detail, $order_detail_item);
        }
        echo json_encode($array_order_detail);
    }
?>