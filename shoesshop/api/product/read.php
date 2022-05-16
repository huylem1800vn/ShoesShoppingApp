<?php 
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/db.php');
    include_once('../../model/product.php');

    $db = new db();
    $connect = $db->connect();
    $product = new product($connect);
    $read = $product->read();

    $num = $read->rowCount();

    if($num > 0){
        $array_product = [];

        while($row = $read->fetch(PDO::FETCH_ASSOC)){
            
            extract($row);

            $product_item = array(
                'id' => $id,
                'name' => $name,
                'price' => $price,
                'promotional_price' => $promotional_price,
                'description' => $description,
                'per_red' => $per_red,
                'image_1' => $image_1,
                'image_2' => $image_2,
                'image_3' => $image_3,
                'brand_id' => $brand_id
            );
            array_push($array_product, $product_item);
        }
        echo json_encode($array_product);
    }
?>