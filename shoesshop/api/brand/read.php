<?php 
    header('Access-Control-Allow-Origin:*');
    header('Content-Type: application/json');

    include_once('../../config/db.php');
    include_once('../../model/brand.php');

    $db = new db();
    $connect = $db->connect();
    $brand = new brand($connect);
    $read = $brand->read();

    $num = $read->rowCount();

    if($num > 0){
        $array_brand = [];

        while($row = $read->fetch(PDO::FETCH_ASSOC)){
            
            extract($row);

            $brand_item = array(
                'id' => $id,
                'name' => $name,
                'description' => $description,
                'image' => $image,
            );
            array_push($array_brand, $brand_item);
        }
        echo json_encode($array_brand);
    }
?>