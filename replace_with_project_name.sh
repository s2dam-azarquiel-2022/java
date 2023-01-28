pn=$1
ph="PLACEHOLDER_PROJECT_NAME"
files=$(rg --color=never -. -l $ph $pn)
sed -Ei "s/$ph/$pn/g" $files