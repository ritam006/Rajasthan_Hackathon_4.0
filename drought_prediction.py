import csv
import pandas as pd
from sklearn.neural_network import MLPRegressor
import numpy as np
from sklearn.metrics import accuracy_score



df=pd.read_csv("./raindata.csv")
l1=[]
l2=[]
for index,x in df.iterrows():
	if(x['SUBDIVISION']=="EAST RAJASTHAN" and x['Parameter']=="Actual"):
		l1.append(x)
	if(x['SUBDIVISION']=="EAST RAJASTHAN" and (x['Parameter']=="Mean" or x['Parameter']=="Standard deviation")):
		l2.append(x)
# print len(l2)
# print len(l)
# with open(, 'wb') as myfile:
#     wr = csv.writer(myfile, quoting=csv.QUOTE_ALL)
#     wr.writerow(l)
months = ['JAN', 'FEB', 'MAR', 'APR', 'MAY', 'JUN', 'JUL', 'AUG', 'SEPT', 'OCT', 'NOV', 'DEC'] 
spi= pd.DataFrame(index=set(zip(*l1)[1]),columns=months)
# print spi
spi = spi.sort_index(ascending=True)
j=0;
for r in l1:
	# print r[1]
	# print spi.index[j]
	if(r[1]==spi.index[j]):
		# print r
		z=0
		for k in range(3,15):
			# print r[k]
			spi.ix[r[1],z]=(r[k]-l2[0][k])/l2[1][k]
			z=z+1
		j=j+1
# print spi


###cumulative###


# print spi.ix[0,0]
cumulated=pd.DataFrame(index=spi.index,columns=['0','1','2','3'])

for a in spi.index:
	# print a
	sum=0
	j=0
	for i in range(0,12):
		sum=sum+spi.ix[a,i]
		if(i==2 or i==5 or i==8 or i==11):
			cumulated.ix[a,j]=sum/3
			sum=0
			j+=1

# cumulated=cumulated.T

# print cumulated


###SVR###
X=[]
z=pd.DataFrame(index=spi.index,columns=['0','1','2','3'])
for i in l1:
	sum=0
	p=0
	for j in range(3,15):
		sum=sum+i[j]
		if(j==5 or j==8 or j==11 or j==14):
			z.ix[i[1],str(p)]=sum
			p=p+1
			sum=0
# print z
Y=[]
for i in spi.index:
	# print i
	if(int(i)==1901):
		p=1
	else:
		p=0
	for j in range(0,4):
		if(p==0):
			s=np.array([p,z.ix[str(int(i)-1),str(p)]])
			X.append(s)
			Y.append(cumulated.ix[str(int(i)-1),str(j)])
		else:
			s=np.array([p,z.ix[i,p]])
			X.append(s)
			Y.append(cumulated.ix[i,j])
		p=p+1
		if p==4:
			break

x_train,y_train,x_test,y_test=[],[],[],[]
m=len(X)
k=int(0.68*m)
n=int(m-k)
for i in range(m):
	if i<=k:
		x_train.append(X[i])
		y_train.append(Y[i])
	else:
		x_test.append(X[i])
		y_test.append(Y[i])

##MLP##
classifier=MLPRegressor(activation='relu',learning_rate='adaptive')
classifier.fit(x_train,y_train)
Y_predict=classifier.predict(x_test)
for i in range(n-1):
	if(Y_predict[i]<-1):
		Y_predict[i]=0
	else:
		Y_predict[i]=1
	if y_test[i]<-1:
		y_test[i]=0
	else:
		y_test[i]=1
print(accuracy_score(y_test,Y_predict))
