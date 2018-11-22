<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="container pt-5 pb-5">
	<h1>Thống kê số lượng và tổng giá trị hàng hóa theo chủng loại</h1>
	<br>
	<table class="table table-sm">
		<tr>
			<th>STT</th>
			<th>Mã hàng hóa</th>
			<th>Tên hàng hóa</th>
			<th>Chủng loại</th>
			<th>Nhà cung cấp</th>
			<th>Kho</th>
			<th>Hạn sử dụng</th>
			<th>Số lượng</th>
		</tr>
		<s:iterator value="expirationGoodsReport" status="status">
			<tr>
				<td>
					<s:property value="#status.count" />
				</td>
				<td>
					<s:property value="code" />
				</td>
				<td>
					<s:property value="name" />
				</td>
				<td>
					<s:property value="category" />
				</td>
				<td>
					<s:property value="supplier" />
				</td>
				<td>
					<s:property value="stock" />
				</td>
				<td>
					<s:property value="expiration" />
				</td>
				<td>
					<s:property value="quantity" default="0" />
				</td>
			</tr>
		</s:iterator>
	</table>
</div>