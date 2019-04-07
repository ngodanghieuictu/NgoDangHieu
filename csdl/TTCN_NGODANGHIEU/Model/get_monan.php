<?php
	require 'connect.php';

	if(isset($_GET['mamonan'])){
		$a = $_GET['mamonan'];
		$query = "SELECT * FROM tbl_monan WHERE ma_monan='$a'";
	}else{
		$query = "SELECT * FROM tbl_monan";
	}
	
	$data = mysqli_query($connect,$query);

	class SanPham{
		function SanPham($id,$tensp,$mota,$gia,$img,$mamonan){
			$this->ID     =$id;
			$this->TSP    =$tensp;
			$this->MT   =$mota;
			$this->GIA     =$gia;
			$this->IMG	  =$img;
			$this->MA	  =$mamonan;
			
		}
	}
	$mang_sp = array();

	
	

	while($row =mysqli_fetch_assoc($data)){
		array_push($mang_sp, new SanPham($row['id'],$row['ten_monan'],$row['mota_monan'],$row['gia_monan'],$row['img_monan'],$row['ma_monan']));
	}
			
	echo json_encode($mang_sp);
	// if($tdn!=null){
	// 	echo "success";
	// }else{
	// 	echo "erro";
	// }

?>