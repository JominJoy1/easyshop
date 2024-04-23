from flask import*
from database import*
import uuid
import qrcode


public=Blueprint('public',__name__)


@public.route('/')
def home():
    return render_template('publichome.html')


@public.route('/login',methods=['get','post'])
def login():
    if'login'in request.form:
        uname=request.form['uname']
        password=request.form['pass']
        
        qry="select * from login where username='%s' and password='%s'"%(uname,password)
        res=select(qry)
        if res:
            session['log']=res[0]['login_id']
            
            if res[0]['usertype']=='admin':
                return ("<script>alert('welcome admin');window.location='/adminhome'</script>")
            elif res[0]['usertype']=='shop':
                qry2="select * from shops where login_id='%s'"%(session['log'])
                res2=select(qry2)
                if res2:
                    session['shop']=res2[0]['shop_id']
                    session['shopname']=res2[0]['shop_name']
                return ("<script>alert('welcome shop');window.location='/shophome'</script>")

                

        
    return render_template("login.html")

@public.route('/reg',methods=['get','post'])
def reg():
    if 'register'in request.form:
        shopname=request.form['shop']
        place=request.form['place']
        landmark=request.form['land']
        phone=request.form['phone']
        email=request.form['email']
        latitude=request.form['latitude']
        longitude=request.form['longitude']
        uname=request.form['uname']
        password=request.form['password']
        
        
        qry="insert into login values(null,'%s','%s','pending')"%(uname,password)
        res=insert(qry)
        
        qry2="insert into shops values(null,'%s','%s','%s','%s','%s','%s','pending','%s','%s')"%(res,shopname,place,landmark,phone,email,latitude,longitude)
        res2=insert(qry2)
      
   
        

        # Data to encode in the QR code
        registration_data = res2  # Replace with your registration data

        # Generate the QR code
        qr = qrcode.QRCode(
            version=1,
            error_correction=qrcode.constants.ERROR_CORRECT_L,
            box_size=10,
            border=4,
        )
        qr.add_data(registration_data)
        qr.make(fit=True)

        # Create an image of the QR code
        img = qr.make_image(fill_color="black", back_color="white")
        img.save("static/shop_qr/"+shopname+".png")  # Save the QR code image to a file

        return ("<script>alert('Registeration Completed ... Please wait for approval');window.location='/shophome'</script>")

        
        
        
    return render_template("shop_reg.html")