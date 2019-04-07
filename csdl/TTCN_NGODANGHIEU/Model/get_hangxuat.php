<?php
	require 'connect.php';
	if (isset($_GET['phanloai'])) {
		$phanloai = $_GET['phanloai'];
		if ($phanloai == 1) {
			$thang =date('m');
			$dieukien ="(SELECT MONTH(tbl_hangxuat.date_xuat)) AND YEAR(tbl_hangxuat.date_xuat) =".date('Y');
		}elseif ($phanloai== 2) {
			$thang =date('Y');
			$dieukien ="(SELECT YEAR(tbl_hangxuat.date_xuat))";
		}
	}
	// else{
	// 		$thang =date('n');
	// 	}
		// $thang =date('n');
	
		$query = "SELECT ten_sp,sl,date_xuat,gia
					FROM tbl_hangxuat,tbl_sp 
					WHERE tbl_sp.id= tbl_hangxuat.id_sp
					AND ".$thang." = ".$dieukien;

	// echo date('m');
	
	$data = mysqli_query($connect,$query);

	class HangXuat{
		function HangXuat($tsp,$sl,$date,$gia){
			$this->TSP    =$tsp;
			$this->SL    =$sl;
			$this->DATE   =$date;
			$this->GIA     =$gia;
		}
	}
	$mang_sp = array();

	
	

	while($row =mysqli_fetch_assoc($data)){
		array_push($mang_sp, new HangXuat($row['ten_sp'],$row['sl'],$row['date_xuat'],$row['gia']));
	}
			
	echo json_encode($mang_sp);
	// if($tdn!=null){
	// 	echo "success";
	// }else{
	// 	echo "erro";
	// }

?>