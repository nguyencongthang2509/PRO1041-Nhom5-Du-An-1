# PRO1041-FALL22
# Bài bảo vệ cuối môn dự án 1 - PRO1041
Đề tài: Phần mềm bán giày Sneaker - Công nghệ sử dụng: Hibernate
- Luồng nghiệp vụ chính của phần mềm: Bán hàng - Trả hàng 
- Luồng bán hàng:
- Bán hàng theo hình thức tại quầy:
1. Khi nhân viên tạo một hóa đơn mới, mặc định trạng thái của hóa đơn sẽ là chờ thanh toán, sau đó nhân viên sẽ thêm những sản phẩm mà khách hàng 
lựa chọn vào giỏ hàng và số lượng ở danh sách sản phẩm cũng bị trừ theo.
2. Trong trường hợp khách hàng đổi ý thì nhân viên có thể giảm số lượng hoặc bỏ hẳn sản phẩm đó ra khỏi giỏ hàng,
sản phẩm bị bỏ sẽ được công lại số lượng vào danh sách sản phẩm.
3. Sau khi thêm xong sản phẩm mà khách yêu cầu vào giỏ hàng thì nhân viên sẽ hỏi và xin thông tin của khách, 
nếu khách hàng không muốn để lại thông tin thì mặc định đó là khách lẻ. Trong trường hợp khách muốn lưu thông tin 
thì nhân viên sẽ tìm kiếm khách hàng đó theo số điện thoại …, để biết xem là khách hàng này đã mua tại 
cửa hàng của mình hay chưa, nếu chưa thì có thể thêm mới khách hàng.
4. Khách hàng khi mua hàng tại cửa hàng sẽ có được rank theo tổng tiền hóa đơn đã mua,
tổng tiền càng cao giảm giá theo phần trăm được áp dụng trực tiếp vào hóa đơn càng lớn.
5. Trong trường hợp mà khách hàng đó muốn quay lại để mua thêm sản phẩm (quên ví ở nhà, có việc cá nhân…) 
mà phía sau lại có khách hàng muốn thanh toán luôn thì nhân viên sẽ tiến hành treo hóa đơn cho khách hàng, 
những sản phẩm mà khách hàng đó đã chọn đều không bị mất, nhân viên sẽ có thể tạo hóa đơn mới để thanh toán
cho khách hàng phía sau. Nếu khách hàng cũ không quay lại thanh toán thì nhân viên có thể gán lại hóa đơn này 
cho khách hàng khác và vẫn có thể chỉnh sửa sản phẩm theo khách hàng mới mong muốn.
6. Tiếp theo đó là đến phần thanh toán thì khách hàng có thể thanh toán được dưới ba hình thức đó là: Tiền mặt, chuyển khoản, kết hợp (cả tiền mặt và chuyển khoản).
Đối với những hình thức có chuyển khoản thì nhân viên phải xin khách hàng mã giao dịch của giao dịch khách vừa thực hiện.
7. Sau khi chọn được hình thức thanh toán thì nhân viên sẽ nhập số tiền mà khách đã giao dịch, 
số tiền phải lớn hơn hoặc bằng tổng tiền cần phải thanh toán thì mới cho thanh toán.
8. Sau đó nhân viên thực hiện thanh toán, hệ thống sẽ hỏi có muốn in hóa đơn hay không?. 
Trạng thái của hóa đơn sau khi thanh toán sẽ trở thành đã thanh toán.

- Bán hàng theo hình thức đặt hàng:
1. Khi khách hàng gọi điện đến cho cửa hàng và chọn được sản phẩm muốn mua, nhân viên sẽ tiến hành tạo một hóa đơn mới, 
hóa đơn mới vừa tạo mặc định trạng thái là chờ thanh toán.
2. Sau khi thêm xong sản phẩm mà khách yêu cầu vào giỏ hàng thì nhân viên sẽ hỏi và xin thông tin của khách,
nếu khách hàng không muốn để lại thông tin thì mặc định đó là khách lẻ. 
Trong trường hợp khách muốn lưu thông tin thì nhân viên sẽ tìm kiếm khách hàng đó theo số điện thoại …, 
để biết xem là khách hàng này đã mua tại cửa hàng của mình hay chưa, nếu chưa thì có thể thêm mới khách hàng.
3. Sau khi có được thông tin của khách hàng, nhân viên sẽ xin thông tin của người nhận hàng
(Tên người nhận, số điện thoại, địa chỉ nhận hàng) và thông tin của người ship (Là nhân viên của cửa hàng).
4. Sau khi có được thông tin của người nhận và người ship hàng, nhân viên sẽ hỏi khách hàng mong muốn nhận hàng 
vào thời gian là bao nhiêu, và khách hàng đó có muốn thanh toán luôn hay không, nếu khách hàng đó muốn thanh toán
luôn thì nhân viên sẽ tiến hành thanh toán cho khách hàng, trường hợp còn lại sẽ thanh toán cho khách khi giao hàng đến nơi cho khách.
5. Sau đó nhân viên sẽ cập nhật trạng thái của hóa đơn thành chờ giao hàng tức là chờ đến khoảng thời gian mà khách mong muốn nhận hàng.
6. Sau khi đến ngày mà khách hẹn lấy hàng thì nhân viên bán hàng sẽ in hóa đơn để nhân viên ship hàng sẽ tiến hành giao hàng, 
trạng thái của hóa đơn sẽ chuyển thành đang giao hàng.
8. Trong trường hợp mà nhân viên giao hàng đã giao đến nơi mà khách hàng đó không nhận luôn được, 
khách hàng đó hẹn vào ngày khác thì nhân viên sẽ tiến hành nhập lại ngày khách mong muốn hẹn lại và 
cập nhật trạng thái của hóa đơn thành khách hẹn lại, và đến ngày khách mong muốn hẹn lại thì tiến hành giao lại hàng.
7. Sau khi đã giao hàng thành công đến cho khách, nhân viên ship hàng sẽ phải kiểm tra xem hóa đơn mình
đang giao đã thanh toán hay chưa, nếu khách chưa thanh toán sẽ thực hiện giao dịch với khách, và sau đó
báo lại cho nhân viên bán hàng, trạng thái của hóa đơn sau đó sẽ chuyển thành đã giao.

- Luồng trả hàng:
1. Khi khách có yêu cầu trả hàng, nhân viên kiểm tra hóa đơn mà khách hàng mang đến xem thời gian thực hiện giao dịch của hóa đơn đã quá 3 ngày hay không?
2. Nếu còn hạn nhân viên ghi lại lý do khách tại sao muốn trả hàng.
3. Khi trả thì tiến hành kiểm tra chất lượng của giày xem còn đủ tiêu chuẩn để trả hay không, không áp dụng trả hàng
cho những sản phẩm đã được giảm giá khuyến mại, nếu đủ điều kiện thì tiến hành trả hàng.
4. Sau đó tiến hành trả hàng, hóa đơn trả hàng sẽ lưu lại ngày trả hàng, tổng tiền hoàn trả, nhân viên trả hàng tại thời điểm đó và lý do trả hàng.
5. Sản phẩm được trả sẽ hoàn lại về danh sách sản phẩm và hóa đơn trả hàng cũng lưu lại được chi tiết những sản phẩm trả lại và số lượng trả lại là bao nhiêu.
6. Nhân viên cũng có thể xem lại được lịch sử những lần trả hàng của hóa đơn đó.
