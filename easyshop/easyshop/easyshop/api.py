from flask import*
from database import*
from datetime import datetime


api=Blueprint('api',__name__)


@api.route('/userregister')
def register():
    data={}
    fname=request.args['fname']
    lname=request.args['lname']
    hname=request.args['hname']
    place=request.args['place']
    landmark=request.args['landmark']
    pincode=request.args['pincode']
    phone=request.args['phone']
    email=request.args['email']
    uname=request.args['uname']
    password=request.args['pass']
    
    
    qry="insert into login values(null,'%s','%s','user')"%(uname,password)
    res=insert(qry)
    
    qry1="insert into users values(null,'%s','%s','%s','%s','%s','%s','%s','%s','%s')"%(res,fname,lname,hname,place,landmark,pincode,phone,email)
    res1=insert(qry1)
    if res1:
        data['status']="success"
    
    return str(data)



@api.route('/loginapi',methods=['get','post'])
def login():
    data={}
    uname=request.args['username']
    password=request.args['password']
    
    
    qry="select * from login inner join users using(login_id)  where username='%s' and password='%s'"%(uname,password)
    res=select(qry)
    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']="failed"
    
    
    return str(data)


@api.route('/products',methods=['get','post'])
def products():
    data={}
    qry="select * from products left join rating using(product_id)"
    # qry="SELECT products.* FROM products LEFT JOIN offer USING (product_id) WHERE offer.product_id IS NULL"
    res=select(qry)
    print(res)
    if res:
        data['status']="success"
        data['data']=res
    data['method']="ok"
    return str(data)

# @api.route('/products')
# def products():
#     data={}
#     qry="select * from rating"
#     res=select(qry)
#     if res:
#         for i in res:
#             id=str(i['product_id'])
#             print(id)
    
#     qry1="select * from products"
#     res1=select(qry1)
#     if res1:
#         for i in res1:
#             proid=str(i['product_id'])
            
#     if proid in 
            
#     return str(data)
            

        

        



# @api.route('/viewselectedproducts')
# def viewselectedproducts():
#     data={}
#     product_id=request.args['product_id']
#     print(product_id,"/////////////////////////////////////////////////////////")
    
#     qry="select * from products inner join stocks using(product_id) where product_id='%s'"%(product_id)
#     res=select(qry)
#     c=str(res[0]['stock_quantity'])
#     if str(c)>'0':
#         data['status']="success"
#         data['data']=res
#     else:
        
#         data['status']='failed'

        
#     data['method']="view"
    
#     return str(data)

# @api.route('/viewselectedproducts')
# def viewselectedproducts():
#     data={}
#     product_id=request.args['product_id']
#     print(product_id,"/////////////////////////////////////////////////////////")
    
    
#     qry1="select * from offer INNER JOIN stocks USING(product_id) where product_id='%s'"%(product_id)
#     res1=select(qry1)
#     if res1:
#         stock_quantity = str(res1[0]['stock_quantity'])
#         if stock_quantity > '0':
#             data['status'] = 'success'
#             data['data'] = res1
#         else:
#             data['status']="failed"
#     else:
        
#         qry="select * from products inner join stocks using(product_id) where product_id='%s'"%(product_id)
#         res=select(qry)
#         if res:
#             data['data'] = res
#             data['status'] = 'success'
#         else:
#             data['status']="failed"
        
#     qry="select * from products inner join stocks using(product_id) where product_id='%s'"%(product_id)
#     res=select(qry)
#     c=str(res[0]['stock_quantity'])
#     if str(c)>'0' and product_id in res1:
#         data['status']="success"
#         data['data']=res1
#     else:
#         data['data']=res
#         data['status']='success'

        
#     data['method']="view"
    
#     return str(data)


@api.route('/viewselectedproducts')
def viewselectedproducts():
    data={}
    product_id=request.args['product_id']
    print(product_id,"/////////////////////////////////////////////////////////")
    
    qry1="select * from offer"
    res1=select(qry1)
    if res1:
        id=str(res1[0]['product_id'])
    
    if product_id==id:
        
        qry2="select * from products inner join offer using(product_id)inner join stocks using(product_id) where product_id='%s'"%(product_id)
        res2=select(qry2)
        print(res2)
        if res2:
            c=str(res2[0]['stock_quantity'])
            if str(c)>'0':
                data['status']="success"
                data['data']=res2
            else:
            
                data['status']='failed'

            
            
    else:
        qry="select * from products inner join stocks using(product_id) where product_id='%s'"%(product_id)
        res=select(qry)
        c=str(res[0]['stock_quantity'])
        if str(c)>'0':
            data['status']="success2"
            data['data']=res
        else:
            
            data['status']='failed'
    data['method']="view"
    return str(data)


@api.route('/cart')
def cart():
    data={}
    product_id=request.args['product_id']
    quantity=request.args['quantity']
    price=request.args['price']
    detailsprice=int(quantity)*int(price)
    shopid=request.args['shopid']
    userid=request.args['userid']
    
  
    
    qry4="select * from order_master where user_id='%s' and status='pending'"%(userid)
    res2=select(qry4)
        
    if res2:
        qry5="update order_master set total_amount=(total_amount+'%s') where user_id='%s'"%(detailsprice,userid)
        res1=update(qry5)
        
        qry3="insert into order_details values(null,'%s','%s','%s','%s','pending')"%(res1,product_id,quantity,detailsprice)
        insert(qry3)
    else:
        qry2="insert into order_master values(null,'%s','%s',curdate(),'%s','pending')"%(userid,shopid,detailsprice)
        res1=insert(qry2)
        qry3="insert into order_details values(null,'%s','%s','%s','%s','pending')"%(res1,product_id,quantity,detailsprice)
        insert(qry3)
        
    data['status']="success"
    data['method']="cart"
        
    return str(data)




@api.route('/newcart')
def newcart():
    data={}
    user_id=request.args['user_id']
    
    
    qry="SELECT * FROM order_master INNER JOIN order_details USING(ordermaster_id) INNER JOIN products USING(product_id) where user_id='%s' and status='pending'"%(user_id)
    res=select(qry)
    print(res,"/////////////////////////////////////////")
    if res:
        data['status']="success"
        data['data']=res
    else:
        data['status']="failed"
    
    return str(data)
    
    
    
@api.route('/remove')
def remove():
    data={}
    user_id=request.args['user_id']
    id=request.args['id']
    amount=request.args['amount']
    
    
    qry2="update order_master set total_amount=(total_amount-'%s') where user_id='%s'"%(amount,user_id)
    update(qry2)
    
    
    qry="delete from order_details where orderdetails_id='%s'"%(id)
    delete(qry)
    
    data['status']="success"
    return str(data)
    
   
    
   
    
    
    
@api.route('/complaintsapi')
def complaints():
    data={}
    user_id=request.args['user_id']
    complaint=request.args['complaint']
    
    
    qry="insert into complaints values(null,'%s','%s','pending',curdate())"%(user_id,complaint)
    res=insert(qry)
    if res:
        data['status']="success"
    data['method']="com"
    return str(data)


@api.route('/complaintreply')
def complaintreply():
    data={}
    user_id=request.args['user_id']
    
    qry="select * from complaints where user_id='%s'"%(user_id)
    res=select(qry)
    if res:
        data['status']="success"
        data['data']=res
    data['method']="reply"
    
    return str(data)


@api.route('/payment')
def payment():
    data={}
    id=request.args['id']
    qry="update order_master set status='payment completed' where ordermaster_id='%s'"%(id)
    update(qry)
    data['status']="success"
    return str(data)



@api.route('/pay')
def pay():
    data={}
    id=request.args['id']
    
    qry="select * from order_master where ordermaster_id='%s'"%(id)
    res=select(qry)
    if res:
        data['status']="success"
        data['data']=res
    data['method']="view"
    return str(data)
    
    
@api.route('/paymentlast')
def paymentlast():
    data={}
    id=request.args['id']
    shopid=request.args['shopid']
    amount=request.args['amount']
    userid=request.args['userid']
    
    qry4=" SELECT * FROM order_details INNER JOIN stocks USING(product_id) WHERE ordermaster_id='%s' "%(id)
    res4=select(qry4)
    print(res4)
    if res4:
        for i in res4:
            a=i['product_id']
            b=i['stock_quantity']
            c=i['quantity']
            
            
            qry5="update stocks set stock_quantity=(stock_quantity-'%s') where product_id='%s'"%(c,a)
            res5=update(qry5)
            print(res5,"ttttttttttttttttttttttttttttttttttt")
            if res5:
                qry6="select * from stocks inner join products using(product_id) where stock_quantity=0"
                res6=select(qry6)
                for i in res6:
                    d=i['stock_quantity']
                    e=i['stock_id']
                    print(d,"ddddddddddddddddddddddddddddddddddddddddddddd")
                    
                    if d=='0':
                    
                        qry7="insert into notification values(null,'%s','pending',curdate(),'out of stock')"%(e)
                        res7=insert(qry7)
                        print(res7,"77777777777777777777777777777777")                        
                
            
            
                
        # stq=res4[0]['stock_quantity']
        # q=res4[0]['quantity']
        
        # a=int(stq)-int(q)
        # print(a,"//////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\")
        
        # qry5="update stocks set stock_quantity='%s' where product_id="
        
    
    
    qry1="update order_master set status='paymentcompleted' where ordermaster_id='%s' "%(id)
    update(qry1)
    
    
    qry="insert into payment values(null,'%s','%s','%s','%s')"%(shopid,id,userid,amount)
    res=insert(qry)
    if res:
        data['status']="success"
        data['data']=res
    data['method']="payment"
    return str(data)

    
@api.route('/notifications')
def notifications():
    data={}
    qry="select * from notification inner join stocks using(stock_id) inner join products using(product_id) where status='stocks available now' and date=curdate()"
    res=select(qry)
    print(res,"//////////////////////////////////////")
    if res:
        data['status']="success" 
        data['data']=res
    else:
        data['status']="failed"  
    return str(data) 


@api.route("/noti",methods=['post','get'])
def noti():
    data={}
    qry="SELECT COUNT(*) as cc FROM notification WHERE status = 'stocks available now' and date=curdate()"
    res=select(qry)
    if res:
        data['status']="success"
        data['data']=res
    data['method']="noti"
    return str(data)
    
@api.route('/orderhistory')
def orderhistory():
    data={}
    user_id=request.args['userid']
    qry="select * from order_master inner join order_details using(ordermaster_id) inner join products using(product_id) where user_id='%s'"%(user_id)
    res=select(qry)
    print(res,"ppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp")
    if res:
        data['status']="success"
        data['data']=res
    return str(data)



@api.route('/user_rate')
def user_rate():
    data={}
    userid=request.args['userid']
    productid=request.args['productid']
    rating=request.args['rating']
    
    
    
    
    qry="insert into rating values(null,'%s','%s','%s')"%(userid,productid,rating)
    res=insert(qry)
    if res:
        data['status']="success"
    return str(data)



# @api.route('/search')
# def search():
#     filter = request.args['filter']
#     content = request.args['content']
#     print(filter,"ppppppppppppppppppppppppppppppppp")

#     # Start building the SQL query
#     qry = "SELECT * FROM products inner join rating using(product_id) WHERE "

#     if 'product_name' in filter:
#         qry += "product_name LIKE '%{}%' OR ".format(content)
    
#     if 'description' in filter:
#         qry += "description LIKE '%{}%' OR ".format(content)
    
#     if 'price' in filter:
#         qry += "price = {} AND ".format(content)
    
#     if 'rating' in filter:
#         qry += "rating = {} AND ".format(content)
    
#     # Remove the trailing 'OR ' or 'AND ' from the query
#     if qry.endswith('OR '):
#         qry = qry[:-3]
#     elif qry.endswith('AND '):
#         qry = qry[:-4]
    
#     # You can add additional conditions or logic as needed
#         print(qry,"////////////////////////////////////////////////////////////////////")

    
#     return qry  # This is the generated SQL query


@api.route('/offer')
def offer():
    data={}
    qry="select * from products inner join offer using(product_id)"
    res=select(qry)
    if res:
        print(res,"llllllllllllllllllllllll")
        enddt=res[0]['end_date']
        print(enddt,"ppppppppppppppppppppp")
        current_date = datetime.now().strftime("%Y-%m-%d")

        
        for i in res:
            edt=i['end_date']
            if edt<current_date:
                qry1="delete from offer where end_date='%s'"%(edt)
                delete(qry1)
            else:
                data['status']="success"
                data['data']=res
    else:
        data['status']="failed"
        

    data['method']="off"

    
    return str(data)



@api.route('/placed')
def placed():
    data={}
    id=request.args['product_id']
    
    qry="select * from products inner join offer using(product_id) inner join stocks using(product_id) inner join shops using(shop_id) where product_id='%s'"%(id)
    res=select(qry)
    if res:
        data['status']="success"
        data['data']=res
    data['method']="view"
    return str(data)


@api.route('/normalplaced')
def normalplaced():
    data={}
    id=request.args['product_id']

    qry="select * from products inner join stocks using(product_id) inner join shops using(shop_id) where product_id='%s'"%(id)
    res=select(qry)
    if res:
        data['status']="success"
        data['data']=res
    data['method']="normal"
    return str(data)



@api.route('/pricesearch')
def pricesearch():
    data={}
    price1=request.args['price1']
    price2=request.args['price2']
    type=request.args['type']
    
    
    # qry="SELECT * FROM products WHERE price BETWEEN '%s' AND '%s' where product_name like '%s'"%(price1,price2,type)
    qry ="SELECT * FROM products left join rating using(product_id) WHERE price BETWEEN '%s' AND '%s' AND products.product_name LIKE '%s'"%(price1,price2,type)
    res=select(qry) 
    print(res)  
    if res:
        data['status']="success"
        data['data']=res
    data['method']="ok"
   
    return str(data)




@api.route('/ratesearch')
def ratesearch():
    data={}
    type=request.args['type']
    # qry="select * from products left join rating using(product_id) order by rating.rating by DESC where products.product_name='%s'"%(type)
    qry = "SELECT * FROM products LEFT JOIN rating USING(product_id) WHERE products.product_name = '%s' ORDER BY rating.rating DESC" % (type)

    res=select(qry)
    print(res)
    if res:
        data['status']="success"
        data['data']=res
    data['method']="ok"
    return str(data)


@api.route('/nearby')
def nearby():
    data={}
    lati=request.args['lati']
    logi=request.args['logi']
    type=request.args['type']
    print(lati,"////////////")
    print(logi,"\\\\\\\\\\\\\\\\\\")
    q="SELECT *,(3959 * ACOS ( COS ( RADIANS('%s') ) * COS( RADIANS( latitude) ) * COS( RADIANS( longitude ) - RADIANS('%s') ) + SIN ( RADIANS('%s') ) * SIN( RADIANS(latitude ) ))) AS user_distance,CONCAT(shops.shop_name,' ',shops.place) AS dname  FROM shops INNER JOIN products  USING(shop_id) INNER JOIN stocks USING(product_id) left JOIN rating USING(product_id) where products.product_name='%s'  HAVING user_distance<31.068 " % (lati,logi,lati,type)
    res=select(q)
    print(res)
    if res:
        data['status']="success"
        data['data']=res
    data['method']="ok"
    return str(data)


    


    



    

    
    
    
    


    


    
    