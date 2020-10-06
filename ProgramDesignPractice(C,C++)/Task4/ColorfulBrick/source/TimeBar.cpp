#include "stdafx.hpp"
#include "TimeBar.hpp"
//#include "Game.hpp"

TimeBar::TimeBar()
{
	_remainingTime = _totalTime;
}


bool TimeBar::isOver(sf::RenderWindow& rw, float elapsedTime)
{
	_remainingTime -= elapsedTime;
	if(_remainingTime <= 0)
	{
		_remainingTime = _totalTime;
		return true;
	}
	if (_remainingTime < 10)
	{
		ServiceLocator::GetAudio()->PlaySound("media/audio/countdown_warning.wav");
	}
	sf::Texture texture;
	texture.loadFromFile("media/images/timebar.png");
	sf::Sprite outside(texture);
	sf::RectangleShape inside(sf::Vector2f(_remainingTime/_totalTime*423,6));
	outside.setPosition(0,0);
	inside.setFillColor(sf::Color::Red);
	inside.setOutlineThickness(0);
	inside.setPosition(102,11);
	rw.draw(outside);
	rw.draw(inside);
	return false;
}

void TimeBar::reset()
{
	_remainingTime = _totalTime;
}

void TimeBar::setTotalTime(float totalTime)
{
	_totalTime = totalTime;
}

float TimeBar::_totalTime = 60;