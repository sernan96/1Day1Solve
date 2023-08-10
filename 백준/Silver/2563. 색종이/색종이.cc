#include<iostream>
using namespace std;
int main()
{
	int paper[100][100] = { 0, };
	int x, y, num, check=0;
	cin >> num;
	for (int i = 0; i < num; i++)
	{
		cin >> x >> y;
		for (int j = 0; j < 10; j++)
		{
			for (int k = 0; k < 10; k++)
			{
				paper[x - 1 + j][y - 1 + k] = 1;
			}
		}
	}
	for (int n = 0; n < 100; n++)
	{
		for (int m = 0; m < 100; m++)
		{
			if (paper[n][m] == 1)
			{
				check++;
			}
		}
	}
	cout << check;
	return 0;
}