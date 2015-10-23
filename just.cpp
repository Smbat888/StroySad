#include <iostream>

using namespace std;

int main()
{
int m,n;
cout << "n="; cin >> n;
cout << "m="; cin >> m;
int c=m-n;
static int count=0;
int x;
while(c==0)
{
x=c/10;
c=x;
x=c/10;
count++;}
cout << count << endl;
return 0;
}
