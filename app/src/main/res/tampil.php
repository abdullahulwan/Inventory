<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST'){
    # code...
    include 'koneksi.php';

    if ($_POST['action'] == 'tampildata') {
        $a = mysqli_query($koneksi, "SELECT * FROM tampildata ORDER BY id_barang ASC");
        $result = [];
        while ($row = mysqli_fetch_array($a)) {
            # code...
            array_push($result, [
                'id_barang' => $row['id_barang'],
                'nama_barang' => $row['nama_barang'], 
                'stock' => $row['stock']  
            ]);
        } 

        echo json_encode([
            'inventory' => $result
        ]);
}

mysqli_close($koneksi);
}