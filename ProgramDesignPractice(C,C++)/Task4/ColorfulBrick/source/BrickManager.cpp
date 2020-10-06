#include "stdafx.hpp"
#include "BrickManager.hpp"
#include "Position.hpp"

BrickManager::BrickManager():
	vertical(12),
	parallel(16),
	width(62),
	height(58)
{
	for (int i=0; i<parallel*vertical; i++)
	{
		_pBricks[i] = NULL;
	}
}

BrickManager::~BrickManager()
{
	for (int i=0; i<parallel*vertical; i++)
	{
		if (_pBricks[i]!=NULL)
		{
			delete _pBricks[i];
		}
		_pBricks[i] = NULL;
	}
}

void BrickManager::initialize()
{
	for (int i=0; i<parallel*vertical; i++)
	{
		if (_pBricks[i] != NULL)
		{
			delete _pBricks[i];
		}
		int randColor = rand()%_rate;
		if (randColor > 7)
		{
			_pBricks[i] = NULL;
		}
		else
		{
			switch (randColor)
			{
			case 0:
				_pBricks[i] = new Purple; break;
			case 1:
				_pBricks[i] = new Golden; break;
			case 2:
				_pBricks[i] = new Greenblue; break;
			case 3:
				_pBricks[i] = new Grey; break;
			case 4:
				_pBricks[i] = new Lightblue; break;
			case 5:
				_pBricks[i] = new Lightgreen; break;
			case 6:
				_pBricks[i] = new Lightpink; break;
			case 7:
				_pBricks[i] = new Orange; break;
			default:
				break;
			}
		}
		Position pos(i);
		if (_pBricks[i] != NULL)
		{
			_pBricks[i]->setPosition(pos.getRect().left, pos.getRect().top);
		}
	}
}

void BrickManager::updateAll(float elapsedTime)
{
	for (int i=0; i<parallel*vertical; i++)
	{
		if (_pBricks[i] != NULL)
		{
			if (_pBricks[i]->update(elapsedTime))
			{
				delete _pBricks[i];
				_pBricks[i] = NULL;
			}
		} 
	}
}

void BrickManager::drallAll(sf::RenderWindow& rw)
{
	for (int i=0; i<parallel*vertical; i++)
	{
		if (_pBricks[i] != NULL)
		{
			_pBricks[i]->draw(rw);
		}
	}
}

VisibleGameObject* BrickManager::operator[](int index)
{
	return _pBricks[index];
}

VisibleGameObject* BrickManager::operator()(sf::Vector2i index)
{
	return _pBricks[index.y*parallel+index.x];
}

int BrickManager::handleClick(Position& pos)
{
	VisibleGameObject* left, *right, *up, *down;
	bool isDrop[4] = {false, false, false, false};
	if (_pBricks[pos.getIndex()] != NULL)
	{
		return 0;
	}
	else
	{
		int x = pos.getVector().x, y = pos.getVector().y;
		while ((*this)(sf::Vector2i(x,y))==NULL && x>=0)
		{
			x--;
		}
		if (x<0)
			left = NULL;
		else
			left = (*this)(sf::Vector2i(x,y));

		x = pos.getVector().x;
		y = pos.getVector().y;
		while ((*this)(sf::Vector2i(x,y))==NULL && x<parallel)
		{
			x++;
		}
		if (x>=parallel)
			right = NULL;
		else
			right = (*this)(sf::Vector2i(x,y));

		x = pos.getVector().x;
		y = pos.getVector().y;
		while ((*this)(sf::Vector2i(x,y))==NULL && y>=0)
		{
			y--;
		}
		if (y<0)
			up = NULL;
		else
			up = (*this)(sf::Vector2i(x,y));

		x = pos.getVector().x;
		y = pos.getVector().y;
		while ((*this)(sf::Vector2i(x,y))==NULL && y<vertical)
		{
			y++;
		}
		if (y>=vertical)
			down = NULL;
		else
			down = (*this)(sf::Vector2i(x,y));

		
		if (left != NULL && left->getState()==1)
		{
			if (right!=NULL && right->getState()==1 && !isDrop[1] && left->getType()==right->getType())
			{
				left->setState(2);
				right->setState(2);
				isDrop[0] = true;
				isDrop[1] = true;
			}
			if (up!=NULL && up->getState()==1 && !isDrop[2] && left->getType()==up->getType())
			{
				left->setState(2);
				up->setState(2);
				isDrop[0] = true;
				isDrop[2] = true;
			}
			if (down!=NULL && down->getState()==1 && !isDrop[3] && left->getType()==down->getType())
			{
				left->setState(2);
				down->setState(2);
				isDrop[0] = true;
				isDrop[3] = true;
			}
		}
		if (right!=NULL && !isDrop[1])
		{
			if (up!=NULL && up->getState()==1 && !isDrop[2] && right->getType()==up->getType())
			{
				right->setState(2);
				up->setState(2);
				isDrop[1] = true;
				isDrop[2] = true;
			}
			if (down!=NULL && down->getState()==1 && !isDrop[3] && right->getType()==down->getType())
			{
				right->setState(2);
				down->setState(2);
				isDrop[1] = true;
				isDrop[3] = true;
			}
		}
		if (up!=NULL && !isDrop[2])
		{
			if (down!=NULL && down->getState()==1 && !isDrop[3] && up->getType()==down->getType())
			{
				up->setState(2);
				down->setState(2);
				isDrop[1] = true;
				isDrop[3] = true;
			}
		}
	}
	int score = 0;
	for (int i=0; i<4; i++)
	{
		if (isDrop[i])
		{
			score += 100;
		}
	}
	if (score == 0)
	{
		score = -100;
	}
	else if (score == 200)
	{
		ServiceLocator::GetAudio()->PlaySound("media/audio/speedmatch1.wav");
	}
	else if(score == 300)
	{
		score += 100;
		ServiceLocator::GetAudio()->PlaySound("media/audio/speedmatch5.wav");
	}
	else if (score == 400)
	{
		score += 200;
		ServiceLocator::GetAudio()->PlaySound("media/audio/speedmatch.wav");
	}
	return score;
}

void BrickManager::setRate(int rate)
{
	_rate = rate;
}

int BrickManager::getRate()
{
	return _rate;
}

int BrickManager::_rate = 16;