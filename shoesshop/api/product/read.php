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
                'productID' => $productID,
                'name' => $name,
                'price' => $price,
                'promotionalPrice' => $promotionalPrice,
                'description' => $description,
                'perRed' => $perRed,
                'image1' => $image1,
                'image2' => $image2,
                'image3' => $image3,
                'image4' => $image4,
                'image5' => $image5,
                'brandID' => $brandID,
                'brandName' => $brandName,
                'categoryID' => $categoryID,
                'categoryName' => $categoryName
            );
            array_push($array_product, $product_item);
        }
        echo json_encode($array_product);
    }
?>