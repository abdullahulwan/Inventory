<?php

include 'koneksi.php';

$id_barang = $_POST['id_barang'];
$nama_barang = $_POST['nama_barang'];
$stock = $_POST['stock'];

if (iss($id_barang) && isset($nama_barang) && isset ($stock)){
    $add_stock_barang = mysqli_query($koneksi, "INSERT INTO stock_barang VALUE('$id_barang','$nama_barang','$stock')");


if ($add_stock_barang){
    $res = [
        $pesan = 'data berhasil di simpan'
    ];
}else {
    $res = [
        $pesan = 'data gagal di simpan'
    ];
}

    echo json_encode(array(
        'status' => $pesan
    ));
}