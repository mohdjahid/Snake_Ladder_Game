#include<stdio.h>
#include<conio.h>
int main()
{
  clrscr();
  for(int i=1;i<=100;i++)
  {
      int r,q,row,col;
      r=i%10;
      q=i/10;
       if(r==0||r==1)
	  {
		if(q%2==0)
			col=0;
		else
			col=9;
	  }
       else
	  {
		if(q%2==0)
			col=r-1;
		else
			col=10-r;
	  }
       printf("%d ",col);
       if(i%10==0)
	   {
		printf("\n");
	    }

  }


  getch();
  return 0;
}